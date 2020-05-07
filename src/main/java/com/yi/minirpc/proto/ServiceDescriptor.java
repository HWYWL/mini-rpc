package com.yi.minirpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 表示服务对象
 *
 * @author YI
 * @date 2020/5/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    /**
     * 类名
     */
    private String clazz;
    /**
     * 方法名
     */
    private String method;
    /**
     * 返回值类型
     */
    private String returnType;
    /**
     * 参数类型
     */
    private String[] parameterType;

    /**
     * 对象信息描述
     *
     * @param clazz  clazz
     * @param method clazz对应的方法
     * @return
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];

        // 获取所有参数类型
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterType(parameterTypes);

        return sdp;
    }

    /**
     * 作为注册服务使用Map中的key，所以必须重写equals
     *
     * @param o 对比数据
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(method, that.method) &&
                Objects.equals(returnType, that.returnType) &&
                Arrays.equals(parameterType, that.parameterType);
    }

    /**
     * 作为注册服务使用Map中的key，所以必须重写hashCode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(clazz, method, returnType);
        result = 31 * result + Arrays.hashCode(parameterType);
        return result;
    }
}