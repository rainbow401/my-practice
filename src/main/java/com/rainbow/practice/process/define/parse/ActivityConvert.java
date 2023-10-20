package com.rainbow.practice.process.define.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.define.element.Activity;
import com.rainbow.practice.process.define.element.Base;
import com.rainbow.practice.process.define.element.BaseType;
import com.rainbow.practice.process.define.element.BaseWithPosition;
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
            throw new IllegalStateException("field 'label' is missing");
        }

        return new Activity(baseWithPosition, labelJson.asText());
    }

    @Override
    public String getSupportType() {
        return BaseType.ACTIVITY.getShape();
    }
}
