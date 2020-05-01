package com.yi.minirpc;

import com.yi.minirpc.codec.Decoder;
import com.yi.minirpc.codec.Encoder;
import com.yi.minirpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 *
 * @author YI
 * @date 2020/5/1 15:36
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        // 调用远程服务
        Response resp = invokeRemote(request);
        if (resp.getCode() != 0) {
            throw new IllegalStateException("错误的方法远程调用：" + resp);
        }
        return resp.getData();
    }

    /**
     * 调用远程服务
     *
     * @param request RPC请求
     * @return
     */
    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response resp;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            resp = decoder.decode(inBytes, Response.class);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setCode(-1);
            resp.setMessage("RpcClient got error：" + e.getCause() + " --> " + e.getMessage());
        } finally {
            // 不为空 放回连接池中
            if (client != null) {
                selector.release(client);
            }
        }

        return resp;
    }
}