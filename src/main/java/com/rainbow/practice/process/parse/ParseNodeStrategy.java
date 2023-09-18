package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.model.Base;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public interface ParseNodeStrategy {

    Base parse(JsonNode node);

}
