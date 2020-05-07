package com.yi.minirpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * 具体服务
 *
 * @author YI
 * @date 2020/5/7
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    /**
     * 服务对象提供者
     */
    private Object target;
    /**
     * 服务对象提供者所对应的方法
     */
    private Method method;
}