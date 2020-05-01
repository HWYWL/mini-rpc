package com.yi.minirpc.example;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author YI
 * @date 2020/5/1
 */
public class CalcServiceImpl implements CalcService {

    @Override
    public String getIp() {
        String ip = null;
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            ip = ip4.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }

    @Override
    public String hi(String name) {
        return "Hi " + name;
    }
}