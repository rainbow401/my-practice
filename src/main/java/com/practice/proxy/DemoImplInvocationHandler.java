package com.practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yanzhihao
 */
public class DemoImplInvocationHandler<T> implements InvocationHandler {

    private T target;

    public DemoImplInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke start");
        Object result = method.invoke(target, args);
        System.out.println("invoke end");
        return result;
    }
}
