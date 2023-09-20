package com.rainbow.practice.process.validation;

import com.rainbow.practice.process.model.Base;
import com.rainbow.practice.process.model.BaseType;

import java.util.List;
import java.util.Objects;

/**
 * @Author: yzh
 * @Date: 2023/9/20
 * @Description:
 */
public class EventValidation implements ProcessValidation{

    @Override
    public void valid(List<Base> nodes) {

        Base startNode = nodes.get(0);
        if (!Objects.equals(startNode.getBaseType(), BaseType.EVENT)) {
            throw new IllegalStateException("The first node is not an event type");
        }

        Base endNode = nodes.get(nodes.size() - 1);
        if (!Objects.equals(endNode.getBaseType(), BaseType.EVENT)) {
            throw new IllegalStateException("The last node is not an event type");
        }
    }
}
