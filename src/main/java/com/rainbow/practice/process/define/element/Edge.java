package com.rainbow.practice.process.define.element;

import lombok.ToString;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@ToString
public class Edge extends Base {

    private String source;

    private String target;

    public Edge(String id, String source, String target) {
        super(id, BaseType.EDGE);
        this.source = source;
        this.target = target;
    }

    public Edge(Base base, String source, String target) {
        super(base.getId(), BaseType.EDGE);
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
