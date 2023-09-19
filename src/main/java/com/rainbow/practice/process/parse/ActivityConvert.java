package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.model.Activity;
import com.rainbow.practice.process.model.Base;
import com.rainbow.practice.process.model.BaseType;
import com.rainbow.practice.process.model.BaseWithPosition;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Slf4j
public class ActivityConvert implements NodeConvert {

    @Override
    public Base parse(JsonNode node) {

        BaseWithPosition baseWithPosition = convertBaseWithPosition(node);
        JsonNode labelJson = node.get("label");
        if (labelJson == null) {
            throw new IllegalStateException("field 'label' is miss");
        }

        return new Activity(baseWithPosition, labelJson.asText());
    }

    @Override
    public String getSupportType() {
        return BaseType.ACTIVITY.getShape();
    }
}
