package com.yi.minirpc;

import com.yi.minirpc.codec.Decoder;
import com.yi.minirpc.codec.Encoder;
import com.yi.minirpc.codec.JSONDecoder;
import com.yi.minirpc.codec.JSONEncoder;
import com.yi.minirpc.transport.TransportClient;
import com.yi.minirpc.transport.impl.HTTPTransportClient;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author YI
 * @date 2020/5/1 15:21
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    /**
     * 随机路由策略
     */
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    /**
     * 默认连接数
     */
    private int connectCount = 1;
    private List<Peer> servers = Collections.singletonList(new Peer("127.0.0.1", 3000));
}