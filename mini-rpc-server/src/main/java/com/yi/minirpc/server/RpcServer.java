package com.yi.minirpc.server;

import com.yi.minirpc.Request;
import com.yi.minirpc.Response;
import com.yi.minirpc.codec.Decoder;
import com.yi.minirpc.codec.Encoder;
import com.yi.minirpc.common.utils.ReflectionUtils;
import com.yi.minirpc.transport.RequestHandler;
import com.yi.minirpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author YI
 * @date 2020/5/1 14:43
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);

                log.info("get request：{}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoker(sis, request);

                resp.setData(ret);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                resp.setCode(-1);
                resp.setMessage("RpcServer get error：" + e.getClass().getName() + " --> " + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);

                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}