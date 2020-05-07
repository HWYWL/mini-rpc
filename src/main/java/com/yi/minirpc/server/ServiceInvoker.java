package com.yi.minirpc.server;

import com.yi.minirpc.common.utils.ReflectionUtils;
import com.yi.minirpc.proto.Request;

/**
 * 调用具体实现
 *
 * @author YI
 * @date 2020/5/7
 */
public class ServiceInvoker {
    /**
     * 执行调用的方法
     *
     * @param service 具体服务对象
     * @param request RPC的一个请求封装
     * @return
     */
    public Object invoker(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}