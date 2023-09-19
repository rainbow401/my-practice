package com.rainbow.practice.process.model;

import lombok.ToString;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@ToString
public class Position {

    private Double x;

    private Double y;

    public Position(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
