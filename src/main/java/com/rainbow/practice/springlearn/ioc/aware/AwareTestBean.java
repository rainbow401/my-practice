package com.rainbow.practice.springlearn.ioc.aware;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.NamedBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.stream.Stream;

public class AwareTestBean implements ApplicationContextAware, BeanNameAware, NamedBean {

    private ApplicationContext ctx;

    private String beanName;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void setBeanName(@NotNull String name) {
        this.beanName = name;
    }

    public void printBeanNames() {
        Stream.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

    public String getName() {
        return beanName;
    }


    @NotNull
    @Override
    public String getBeanName() {
        return this.beanName;
    }
}
