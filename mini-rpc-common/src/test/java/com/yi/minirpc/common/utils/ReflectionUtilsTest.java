package com.yi.minirpc.common.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    public void testGetPublicMethod() {
        Method[] methods = ReflectionUtils.getPublicMethod(TestClass.class);
        assertEquals(1, methods.length);

        String name = methods[0].getName();
        assertEquals("b", name);
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethod(TestClass.class);
        TestClass t = ReflectionUtils.newInstance(TestClass.class);

        Method b = methods[0];
        Object invoke = ReflectionUtils.invoke(t, b);
        assertEquals("b", invoke);
    }
}