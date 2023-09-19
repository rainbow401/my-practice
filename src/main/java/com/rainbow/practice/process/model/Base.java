package com.rainbow.practice.process.model;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class Base extends BaseTreeNode{

    private String id;

    private BaseType baseType;

    public Base(String id, BaseType baseType) {
        this.id = id;
        this.baseType = baseType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseType getBaseType() {
        return baseType;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                ", baseType=" + baseType +
                '}';
    }

    public void setBaseType(BaseType baseType) {
        this.baseType = baseType;
    }
}
