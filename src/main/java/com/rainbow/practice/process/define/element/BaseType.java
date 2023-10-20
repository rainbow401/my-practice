package com.rainbow.practice.process.define.element;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public enum BaseType {

    EVENT(1, "事件", "event"),
    GATEWAY(2, "网关", "gateway"),
    EDGE(3, "连线", "bpmn-edge"),
    ACTIVITY(4, "节点", "activity");


    private final Integer type;
    private final String msg;
    private final String shape;

    BaseType(Integer type, String msg, String shape) {
        this.type = type;
        this.msg = msg;
        this.shape = shape;
    }

    public Integer getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public String getShape() {
        return shape;
    }
}
