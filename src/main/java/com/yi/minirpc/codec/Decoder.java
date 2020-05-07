package com.yi.minirpc.codec;

/**
 * 反序列化接口
 *
 * @author YI
 * @date 2020/5/1
 */
public interface Decoder {
    /**
     * 反序列化接口
     *
     * @param bytes 反序列化数据
     * @param clazz 反序列化类型
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}