package com.rainbow.practice.springlearn.ioc.lazyinject;

import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;

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
