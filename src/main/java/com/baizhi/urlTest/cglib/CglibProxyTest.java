package com.baizhi.urlTest.cglib;

import com.baizhi.urlTest.BuyHouse;
import com.baizhi.urlTest.BuyHouseImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 16:00
 * @Version 1.0
 */
public class CglibProxyTest {
    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(BuyHouseImp.class);
        enhancer.setCallback(new CglibProxy());
        BuyHouseImp buyHouseImp =(BuyHouseImp) enhancer.create();
        buyHouseImp.buyHosue();
    }
}
