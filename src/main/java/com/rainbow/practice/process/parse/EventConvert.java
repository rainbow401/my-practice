package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.model.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Slf4j
public class EventConvert implements NodeConvert {

    @Override
    public Base parse(JsonNode node) {

        BaseWithPosition baseWithPosition = convertBaseWithPosition(node);
        return new Event(baseWithPosition);
    }

    @Override
    public String getSupportType() {
        return BaseType.EVENT.getShape();
    }
}
