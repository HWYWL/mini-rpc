package com.yi.minirpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * 反射工具类
 *
 * @author YI
 * @date 2020/5/1 11:21
 */
public class ReflectionUtils {
    /**
     * 根据class创建对象
     *
     * @param clazz 待创建对象的类
     * @param <T>   对象类型
     * @return 创建好的对象类型
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取类中的所有公共方法
     *
     * @param clazz 类的字节码对象
     * @return 当前类声明public的方法
     */
    public static Method[] getPublicMethod(Class clazz) {
        // 返回当前类的所有方法(不包含父类)
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList<Object> pmethods = new ArrayList<>();
        for (Method m : methods) {
            // 判断是否为public方法，如果是则放入list
            if (Modifier.isPublic(m.getModifiers())) {
                pmethods.add(m);
            }
        }

        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     *
     * @param obj    被调用方法的对象
     * @param method 被调用的方法
     * @param args   方法的参数
     * @return 返回调用的结果
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}