package com.yi.minirpc.example;

import com.yi.minirpc.server.RpcServer;
import com.yi.minirpc.server.RpcServerConfig;

/**
 * @author YI
 * @date 2020/5/1
 */
public class Server {
    public static void main(String[] args) {
        RpcServerConfig config = new RpcServerConfig();
        config.setPort(8080);

        RpcServer server = new RpcServer(config);
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}