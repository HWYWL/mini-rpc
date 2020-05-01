package com.yi.minirpc.codec;

/**
 * 反序列化接口
 * @author YI
 * @date 2020/5/1 13:00
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}