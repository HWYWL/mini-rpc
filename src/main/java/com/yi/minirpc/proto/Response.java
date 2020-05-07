package com.yi.minirpc.proto;

import lombok.Data;

/**
 * RPC响应返回
 *
 * @author YI
 * @date 2020/5/1
 */
@Data
public class Response {
    /**
     * 服务返回编码，0：成功、非0表示失败
     */
    private int code = 0;
    /**
     * 具体失败的错误原因
     */
    private String message = "OK";
    /**
     * 返回的数据
     */
    private Object data;
}