package com.rainbow.practice.mybatisplus.convertDto;

import lombok.Data;

@Data
public class Demo {

    @QueryExpression(value = Type.EQ)
    private String name;
}