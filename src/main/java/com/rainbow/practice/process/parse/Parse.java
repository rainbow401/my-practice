package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.practice.process.model.Base;
import com.rainbow.practice.process.model.BaseTreeNode;
import com.rainbow.practice.process.model.Gateway;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Slf4j
public class Parse {

    private static final String PATH = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\process\\testdata\\data.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final HashMap<String, NodeConvert> convertMap = new HashMap<>();

    static {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ActivityConvert activityConvert = new ActivityConvert();
        EdgeConvert edgeConvert = new EdgeConvert();
        EventConvert eventConvert = new EventConvert();
        GatewayConvert gatewayConvert = new GatewayConvert();

        convertMap.put(activityConvert.getSupportType(), activityConvert);
        convertMap.put(edgeConvert.getSupportType(), edgeConvert);
        convertMap.put(eventConvert.getSupportType(), eventConvert);
        convertMap.put(gatewayConvert.getSupportType(), gatewayConvert);
    }

    public static List<Base> parse(String jsonStr) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        List<Base> baseList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            log.debug("node: {}", node);

            String shape = node.get("shape").asText();
            if (StringUtils.isBlank(shape)) {
                log.info("shape is blank");
                continue;
            }

            NodeConvert convert = convertMap.get(shape);
            if (convert == null) {
                log.error("convert is null");
                continue;
            }

            Base base = convert.parse(node);
            baseList.add(base);
        }

        log.debug("baseList: {}", baseList);

        return baseList;
    }

    public BaseTreeNode parseTree(List<Base> data) {


        return null;
    }

    public static void main(String[] args) throws IOException {
        String s = FileUtils.readFileToString(new File(PATH));
        parse(s);
    }
}
