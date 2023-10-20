package com.rainbow.practice.process.define.element;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public enum GatewayType {

    PARALLEL(1, "并行"),
    EXCLUSIVE(2, "排他"),
    INCLUSIVE(3, "包容");

    GatewayType(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    private final Integer type;
    private final String msg;
}
