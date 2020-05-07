package com.yi.minirpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网络传输端点
 * @author YI
 * @date 2020/5/1
 */
@Data
@AllArgsConstructor
public class Peer {
    /**
     * 远程通信地址
     */
    private String host;
    /**
     * 远程通信端口号
     */
    private int port;
}