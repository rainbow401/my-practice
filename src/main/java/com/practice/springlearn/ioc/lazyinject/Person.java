package com.practice.springlearn.ioc.lazyinject;

import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
@Data
public class Person {

//    @Autowired
    private ObjectProvider<Dog> dog;

//    @Autowired
//    public void setDog(ObjectProvider<Dog> dog) {
//        this.dog = dog.getIfAvailable();
//    }
}
