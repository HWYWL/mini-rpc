package com.yi.minirpc.codec;

/**
 * 序列化接口
 *
 * @author YI
 * @date 2020/5/1
 */
public interface Encoder {
    /**
     * 序列化接口
     *
     * @param obj 序列化数据
     * @return
     */
    byte[] encode(Object obj);
}