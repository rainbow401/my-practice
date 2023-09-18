package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@JsonTypeName("bpmn-edge")
public class Edge extends Base {

    private String source;

    private String target;

    @JsonCreator
    public Edge(@JsonProperty("id") String id, @JsonProperty("source") String source, @JsonProperty("target") String target) {
        super(id, BaseType.EDGE);
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
