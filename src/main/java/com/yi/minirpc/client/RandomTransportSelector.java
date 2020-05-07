package com.yi.minirpc.client;

import com.yi.minirpc.common.utils.ReflectionUtils;
import com.yi.minirpc.proto.Peer;
import com.yi.minirpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 实现随机路由策略
 *
 * @author YI
 * @date 2020/5/7
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 所有已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        this.clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        // 创建连接
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("已建立连接的服务：{}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int anInt = new Random().nextInt(clients.size());
        // 随机删除池中的一个连接
        return clients.remove(anInt);
    }

    @Override
    public synchronized void release(TransportClient client) {
        // 把释放的链接放回池中
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        // 清除池中所有连接
        for (TransportClient client : clients) {
            client.close();
        }

        clients.clear();
    }
}