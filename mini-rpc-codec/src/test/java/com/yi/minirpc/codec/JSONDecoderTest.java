package com.yi.minirpc.codec;

import junit.framework.TestCase;

public class JSONDecoderTest extends TestCase {

    public void testDecode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setAge(18);
        bean.setName("校花");

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

        Decoder decoder = new JSONDecoder();
        TestBean decode = decoder.decode(bytes, TestBean.class);
        assertEquals(18, decode.getAge());
        assertEquals("校花", decode.getName());
    }
}