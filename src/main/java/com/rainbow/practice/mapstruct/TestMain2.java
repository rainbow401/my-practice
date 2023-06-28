package com.rainbow.practice.mapstruct;

import com.rainbow.practice.mapstruct.model.Demo1;
import com.rainbow.practice.mapstruct.model.Demo2;

/**
 * @author yanzhihao
 * @since 2023/6/28
 */
public class TestMain2 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.setUsername("1");
        Demo2 result = Convert.CONVERT.convert2(demo1);
        System.out.println("result = " + result);
    }
}
