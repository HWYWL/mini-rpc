package com.yi.minirpc.codec;

/**
 * 序列化接口
 *
 * @author YI
 * @date 2020/5/1 11:51
 */
public interface Encoder {
    byte[] encode(Object obj);
}