package com.rainbow.practice.process.model;

import com.rainbow.practice.tree.Node;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Getter
@Setter
@ToString
public class Base extends BaseTreeNode {

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

    public void setBaseType(BaseType baseType) {
        this.baseType = baseType;
    }
}
