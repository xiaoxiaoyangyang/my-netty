package com.baizhi.urlTest.jdk;

import com.baizhi.urlTest.BuyHouse;
import com.baizhi.urlTest.BuyHouseImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.ProtectionDomain;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 17:28
 * @Version 1.0
 */
public class DynamicProxyHandler<T> implements InvocationHandler {
    Class targetInterface;
    public DynamicProxyHandler(Class targetInterface){
        this.targetInterface=targetInterface;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String declaringClass = method.getDeclaringClass().getName();
        System.out.println(declaringClass);
        return null;
    }
}
