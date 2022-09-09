package com.practice.domain.springlearn.ioc.factorybean;

import lombok.Data;

@Data
public class Child {

    private String wantToy = "ball";

    public String getWantToy() {
        return wantToy;
    }
}
