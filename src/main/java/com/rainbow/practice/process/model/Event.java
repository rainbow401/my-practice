package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.*;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
//@JsonTypeName("event")
public class Event extends BaseWithPosition{

    @JsonCreator
    public Event(@JsonProperty("id") String id, @JsonProperty("position") Position position) {
        super(id, BaseType.EVENT, position);
    }

}
