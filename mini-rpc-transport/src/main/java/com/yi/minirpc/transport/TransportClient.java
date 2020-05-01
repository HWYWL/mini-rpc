package com.yi.minirpc.transport;

import com.yi.minirpc.Peer;

import java.io.InputStream;

/**
 * 客户端通信接口
 *
 * @author YI
 * @date 2020/5/1 13:19
 */
public interface TransportClient {
    /**
     * 创建连接
     *
     * @param peer
     */
    void connect(Peer peer);

    /**
     * 写入数据
     *
     * @param data 待写入的流数据
     * @return
     */
    InputStream write(InputStream data);

    /**
     * 关闭连接
     */
    void close();
}