package com.yi.minirpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化实现
 *
 * @author YI
 * @date 2020/5/1
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}