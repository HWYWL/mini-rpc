package com.yi.minirpc.server;

import com.yi.minirpc.Request;
import com.yi.minirpc.ServiceDescriptor;
import com.yi.minirpc.common.utils.ReflectionUtils;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

public class ServiceManagerTest extends TestCase {
    ServiceManager sm = new ServiceManager();


    @Test
    public void testRegister() {
        TestService service = new TestServiceImpl();
        sm.register(TestService.class, service);
    }

    @Test
    public void testLookup() {
        // 注册
        TestService service = new TestServiceImpl();
        sm.register(TestService.class, service);

        // 构建
        Method method = ReflectionUtils.getPublicMethod(TestService.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestService.class, method);

        Request request = new Request();
        request.setService(sdp);

        // 查找
        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}