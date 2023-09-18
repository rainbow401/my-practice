package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "shape")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Event.class, name = "event"),
        @JsonSubTypes.Type(value = Activity.class, name = "activity"),
        @JsonSubTypes.Type(value = Edge.class, name = "bpmn-edge"),
        @JsonSubTypes.Type(value = Gateway.class, name = "gateway"),
})
public class Base {

    @JsonProperty("id")
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
