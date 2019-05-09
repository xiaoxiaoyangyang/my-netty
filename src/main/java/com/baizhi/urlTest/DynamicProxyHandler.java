package com.baizhi.urlTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/3/21 15:41
 * @Version 1.0
 */
public class DynamicProxyHandler<T> implements InvocationHandler {
    private T object;
    public DynamicProxyHandler(final T object){
        this.object=object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备");
        Object invoke = method.invoke(object, args);
        System.out.println("买房后的");
        return invoke;
    }
}
