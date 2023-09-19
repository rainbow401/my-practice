package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@ToString
public class Gateway extends BaseWithPosition{

    private GatewayType gatewayType;

    public Gateway(String id, Position position) {
        super(id, BaseType.GATEWAY, position);
    }

    public Gateway(String id, Position position, GatewayType gatewayType) {
        super(id, BaseType.GATEWAY, position);
        this.gatewayType = gatewayType;
    }

    public Gateway(BaseWithPosition baseWithPosition, GatewayType gatewayType) {
        super(baseWithPosition.getId(), BaseType.GATEWAY, baseWithPosition.getPosition());
        this.gatewayType = gatewayType;
    }


    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(GatewayType gatewayType) {
        this.gatewayType = gatewayType;
    }
}
