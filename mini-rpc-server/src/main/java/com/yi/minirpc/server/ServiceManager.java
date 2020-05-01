package com.yi.minirpc.server;

import com.yi.minirpc.Request;
import com.yi.minirpc.ServiceDescriptor;
import com.yi.minirpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 *
 * @author YI
 * @date 2020/5/1 14:00
 */
@Slf4j
public class ServiceManager {
    /**
     * 注册信息管理
     */
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务
     *
     * @param interfaceClass 接口clazz
     * @param bean           接口子类具体实现的对象
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        // 获取所有公共方法
        Method[] methods = ReflectionUtils.getPublicMethod(interfaceClass);

        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);

            services.put(sdp, sis);
            log.info("注册服务：{} ,注册方法：{}", sdp.getClazz(), sdp.getMethod());
        }
    }

    /**
     * 查找注册信息
     *
     * @param request RPC的一个请求封装
     * @return
     */
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}