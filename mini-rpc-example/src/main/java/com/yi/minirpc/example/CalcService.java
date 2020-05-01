package com.yi.minirpc.example;

/**
 * 接口
 *
 * @author YI
 * @date 2020/5/1
 */
public interface CalcService {
    /**
     * 获取IP地址
     *
     * @return
     */
    String getIp();

    /**
     * 打招呼
     *
     * @param name 名称
     * @return
     */
    String hi(String name);
}