package com.rainbow.practice.process.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yzh
 * @Date: 2023/10/20
 * @Description:
 */
@Getter
@Setter
public class Condition {

    private String param;

    private ConditionType type;

    private Object value;
}
