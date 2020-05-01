package com.yi.minirpc;

import lombok.Data;

/**
 * RPC的一个请求封装
 *
 * @author YI
 * @date 2020/5/1 11:13
 */
@Data
public class Request {
    /**
     * 请求服务
     */
    private ServiceDescriptor service;
    /**
     * 请求参数
     */
    private Object[] parameters;
}