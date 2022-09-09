package com.practice.domain.springlearn.ioc.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Data
public class Person {

//    @Autowired
//    @Qualifier(value = "dog2")
    @Resource
    private Dog dog;
}
