package com.yi.minirpc.transport;

/**
 * 服务端通信接口
 *
 * @author YI
 * @date 2020/5/1 13:23
 */
public interface TransportServer {
    /**
     * 初始化监听
     *
     * @param port    端口号
     * @param handler 数据处理器
     */
    void init(int port, RequestHandler handler);

    /**
     * 启动监听
     */
    void start();

    /**
     * 关闭监听
     */
    void stop();

}