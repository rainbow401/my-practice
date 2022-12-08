package com.practice.springlearn.ioc.factorybean;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

@Data
public class ToyFactoryBean implements FactoryBean<Toy> {

    private Child child;

    @Override
    public Toy getObject() throws Exception {
        switch (child.getWantToy()) {
            case "ball":
                return new Ball("ball");
            case "car":
                return new Car("car");
            default:
                // SpringFramework2.0开始允许返回null
                // 之前的1.x版本是不允许的
                return null;
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Toy.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
