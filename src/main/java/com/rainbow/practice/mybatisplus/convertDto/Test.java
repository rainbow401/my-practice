package com.rainbow.practice.mybatisplus.convertDto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class Test {

    public static void main(String[] args) throws IllegalAccessException {
        Demo demo = new Demo();
        demo.setName("1");

        QueryWrapper<Test> convert = QueryUtil.convert(demo);
    }


}
