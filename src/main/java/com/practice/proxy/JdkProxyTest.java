package com.practice.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yanzhihao
 */
public class JdkProxyTest {


    public static void main(String[] args) {
        Demo demo = new DemoImpl();
        DemoImplInvocationHandler<Demo> invocationHandler = new DemoImplInvocationHandler<>(demo);
        Demo proxyDemo = (Demo) Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class[]{Demo.class}, invocationHandler);
        proxyDemo.say();
    }
}
