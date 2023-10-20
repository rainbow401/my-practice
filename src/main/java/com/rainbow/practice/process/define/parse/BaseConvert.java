package com.rainbow.practice.process.define.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.rainbow.practice.process.define.element.Base;

/**
 * @Author: yzh
 * @Date: 2023/9/19
 * @Description:
 */
public class BaseConvert implements NodeConvert{

    @Override
    public Base parse(JsonNode node) {
        return null;
    }

    @Override
    public String getSupportType() {
        return null;
    }
}
