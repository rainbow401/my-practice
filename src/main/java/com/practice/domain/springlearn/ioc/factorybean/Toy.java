package com.practice.domain.springlearn.ioc.factorybean;

import lombok.Data;

@Data
public class Toy {

    private String name;

    public Toy(String name) {
        System.out.println("toy 构造函数");
        this.name = name;
    }
}
