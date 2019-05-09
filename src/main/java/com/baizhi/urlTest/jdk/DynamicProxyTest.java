package com.baizhi.urlTest.jdk;

import com.baizhi.urlTest.BuyHouse;
import com.baizhi.urlTest.BuyHouseImpl;
import com.baizhi.urlTest.DynamicProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 17:35
 * @Version 1.0
 */
public class DynamicProxyTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        BuyHouse buyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new com.baizhi.urlTest.jdk.DynamicProxyHandler<BuyHouse>(BuyHouse.class));
        buyHouse.buyHosue();


    }
}
