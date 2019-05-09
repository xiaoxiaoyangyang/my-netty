package com.baizhi.urlTest;

import java.lang.reflect.Proxy;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 15:45
 * @Version 1.0
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new DynamicProxyHandler(buyHouse));
        proxyBuyHouse.buyHosue();
    }
}
