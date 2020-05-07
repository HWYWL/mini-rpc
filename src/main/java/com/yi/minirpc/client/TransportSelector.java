package com.yi.minirpc.client;

import com.yi.minirpc.proto.Peer;
import com.yi.minirpc.transport.TransportClient;

import java.util.List;

/**
 * 选择server去连接
 *
 * @author YI
 * @date 2020/5/7
 */
public interface TransportSelector {
    /**
     * 初始化selector
     *
     * @param peers 可以连接的server端点信息
     * @param count client与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个Transport与server做交互
     *
     * @return client
     */
    TransportClient select();

    /**
     * 释放用完的client
     *
     * @param client
     */
    void release(TransportClient client);

    void close();
}