package com.rainbow.practice.proxy;

import com.rainbow.practice.proxy.Demo;

public class DemoImpl implements Demo {

    @Override
    public void say() {
        System.out.println("say");
    }
}