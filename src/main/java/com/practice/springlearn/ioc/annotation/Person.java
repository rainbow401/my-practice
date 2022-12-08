package com.practice.springlearn.ioc.annotation;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
@Data
public class Person {

//    @Autowired
//    @Qualifier(value = "dog2")
    @Resource
    private Dog dog;
}
