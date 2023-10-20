package com.rainbow.practice.process.define.element;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class ExclusiveGateway extends Gateway{

    public ExclusiveGateway(String id, Position position) {
        super(id, position, GatewayType.EXCLUSIVE);
    }
}
