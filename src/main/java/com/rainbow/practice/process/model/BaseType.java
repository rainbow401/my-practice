package com.rainbow.practice.process.model;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public enum BaseType {

    EVENT(1, "事件"),
    GATEWAY(2, "网关"),
    EDGE(3, "连线"),
    ACTIVITY(4, "节点");


    private final Integer type;
    private final String msg;

    BaseType(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
