package com.rainbow.practice.process.define.element;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class ParallelGateway extends Gateway {

    public ParallelGateway(String id, Position position) {
        super(id, position, GatewayType.PARALLEL);
    }
}
