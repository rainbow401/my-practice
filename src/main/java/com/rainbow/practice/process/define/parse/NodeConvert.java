package com.rainbow.practice.process.define.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.define.element.Base;
import com.rainbow.practice.process.define.element.BaseWithPosition;
import com.rainbow.practice.process.define.element.Position;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public interface NodeConvert {

    Base parse(JsonNode node);

    String getSupportType();

    default Base convertBase(JsonNode node) {

        JsonNode idJson = node.get("id");
        if (idJson == null) {
            throw new IllegalStateException("field 'id' is missing");
        }

        String id = node.get("id").asText();
        if (StringUtils.isBlank(id)) {
            throw new IllegalStateException("id is blank");
        }

        return new Base(id, null);
    }

    default BaseWithPosition convertBaseWithPosition(JsonNode node) {

        Base base = convertBase(node);

        JsonNode positionJson = node.get("position");
        if (positionJson == null) {
            throw new IllegalStateException("field 'position' is missing");
        }

        JsonNode jsonX = positionJson.get("x");
        if (jsonX == null) {
            throw new IllegalStateException("field 'position.x' is missing");
        }

        JsonNode jsonY = positionJson.get("y");
        if (jsonY == null) {
            throw new IllegalStateException("field 'position.y' is missing");
        }

        return new BaseWithPosition(base, new Position(jsonX.asDouble(), jsonY.asDouble()));
    }
}
