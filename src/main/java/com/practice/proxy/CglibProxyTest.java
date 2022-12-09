package com.practice.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yanzhihao
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        DemoImpl demo = new DemoImpl();
        DemoImpl demo2 = (DemoImpl)Enhancer.create(DemoImpl.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib");
                System.out.println("invoke start");
                Object result = method.invoke(demo, objects);
                System.out.println("invoke end");
                return result;
            }
        });

        demo2.say();
    }
}
