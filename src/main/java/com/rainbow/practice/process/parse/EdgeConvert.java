package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.model.Base;
import com.rainbow.practice.process.model.BaseType;
import com.rainbow.practice.process.model.Edge;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Slf4j
public class EdgeConvert implements NodeConvert {

    @Override
    public Base parse(JsonNode node) {

        Base base = convertBase(node);
        JsonNode sourceJson = node.get("source");
        if (sourceJson == null) {
            throw new IllegalStateException("field 'source' is miss");
        }

        JsonNode targetJson = node.get("target");
        if (targetJson == null) {
            throw new IllegalStateException("field 'target' is miss");
        }

        return new Edge(base, sourceJson.asText(), targetJson.asText());
    }

    @Override
    public String getSupportType() {
        return BaseType.EDGE.getShape();
    }
}
