package com.rainbow.practice.process.define.element;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class Event extends BaseWithPosition{

    public Event(String id, Position position) {
        super(id, BaseType.EVENT, position);
    }

    public Event(BaseWithPosition baseWithPosition) {
        super(baseWithPosition.getId(), BaseType.EVENT, baseWithPosition.getPosition());
    }

}
