package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.ToString;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@ToString
@JsonTypeName("activity")
public class Activity extends BaseWithPosition{

    private String label;

    @JsonCreator
    public Activity(@JsonProperty("id") String id,@JsonProperty("position") Position position,@JsonProperty("label") String label) {
        super(id, BaseType.ACTIVITY, position);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
