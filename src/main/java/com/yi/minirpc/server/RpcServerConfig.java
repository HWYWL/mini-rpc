package com.yi.minirpc.server;

import com.yi.minirpc.codec.Decoder;
import com.yi.minirpc.codec.Encoder;
import com.yi.minirpc.codec.JSONDecoder;
import com.yi.minirpc.codec.JSONEncoder;
import com.yi.minirpc.transport.TransportServer;
import com.yi.minirpc.transport.impl.HTTPTransportServer;
import lombok.Data;

/**
 * server配置
 *
 * @author YI
 * @date 2020/5/7
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    /**
     * 默认端口号
     */
    private int port = 3000;
}