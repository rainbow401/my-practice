package com.rainbow.practice.springlearn.ioc.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuickStartConfiguration {

    @Bean
    public Person person() {
        return new Person();
    }

    @Bean
    public Dog dog1() {
        Dog dog = new Dog();
        return dog;
    }

//    @Bean
//    public Dog dog2() {
//        Dog dog = new Dog();
//        dog.setName("dog2");
//        return dog;
//    }
}
