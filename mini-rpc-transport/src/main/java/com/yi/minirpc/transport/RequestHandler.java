package com.yi.minirpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的Handler
 *
 * @author YI
 * @date 2020/5/1 13:25
 */
public interface RequestHandler {
    /**
     * 处理数据
     *
     * @param receive 数据输入
     * @param toResp 数据输出
     */
    void onRequest(InputStream receive, OutputStream toResp);
}