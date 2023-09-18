package com.rainbow.practice.process.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@JsonTypeName("gateway")
public class Gateway extends BaseWithPosition{

    private GatewayType gatewayType;

    @JsonCreator
    public Gateway(@JsonProperty("id")String id, @JsonProperty("position") Position position) {
        super(id, BaseType.GATEWAY, position);
    }

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
