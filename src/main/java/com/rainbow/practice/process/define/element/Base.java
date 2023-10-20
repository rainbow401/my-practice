package com.rainbow.practice.process.define.element;

import com.rainbow.practice.process.condition.Condition;
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

    private List<Condition> conditions;

    public Base(String id, BaseType baseType) {
        this.id = id;
        this.baseType = baseType;
    }
}
