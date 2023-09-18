package com.rainbow.practice.process.model;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class Gateway extends BaseWithPosition{

    private GatewayType gatewayType;

    public Gateway(String id, Position position, GatewayType gatewayType) {
        super(id, BaseType.GATEWAY, position);
        this.gatewayType = gatewayType;
    }

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(GatewayType gatewayType) {
        this.gatewayType = gatewayType;
    }
}
