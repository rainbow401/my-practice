package com.rainbow.practice.process.model;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class Activity extends BaseWithPosition{

    private String label;

    public Activity(String id, BaseType baseType, Position position) {
        super(id, baseType, position);
    }
}
