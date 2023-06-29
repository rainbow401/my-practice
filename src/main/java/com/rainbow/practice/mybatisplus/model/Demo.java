package com.rainbow.practice.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@TableName("user")
public class Demo {

    private Long id;

    public Demo(Long id) {
        this.id = id;
    }
}
