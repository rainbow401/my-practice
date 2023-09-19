package com.rainbow.practice.process.model;

import lombok.ToString;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@ToString
public class Activity extends BaseWithPosition{

    private String label;

    public Activity(String id, Position position, String label) {
        super(id, BaseType.ACTIVITY, position);
        this.label = label;
    }

    public Activity(BaseWithPosition baseWithPosition, String label) {
        super(baseWithPosition.getId(), BaseType.ACTIVITY, baseWithPosition.getPosition());
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
