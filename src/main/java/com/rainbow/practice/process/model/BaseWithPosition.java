package com.rainbow.practice.process.model;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class BaseWithPosition extends Base{

    private Position position;

    public BaseWithPosition(String id, BaseType baseType, Position position) {
        super(id, baseType);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
