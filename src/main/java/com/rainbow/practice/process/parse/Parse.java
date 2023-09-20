package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.rainbow.practice.process.model.Base;
import com.rainbow.practice.process.model.BaseType;
import com.rainbow.practice.process.model.Edge;
import com.rainbow.practice.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
@Slf4j
public class Parse {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ObjectWriter prettyPrinter = objectMapper.writerWithDefaultPrettyPrinter();

    private static final HashMap<String, NodeConvert> convertMap = new HashMap<>();

    private Base root;

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

    public void setRoot(Base ROOT) {
        this.root = ROOT;
    }

    public Base getRoot() {
        return root;
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

    public void parseTreeRoot(List<Base> data) throws JsonProcessingException {

        Map<String, Base> dataMap = new HashMap<>();
        List<Base> edges = new ArrayList<>();
        for (Base e : data) {
            BaseType baseType = e.getBaseType();
            if (Objects.equals(baseType, BaseType.EDGE)) {
                edges.add(e);
            } else {
                dataMap.put(e.getId(), e);
            }
        }

        for (Base e : edges) {
            Edge edge= (Edge) e;
            String parentId = edge.getSource();
            String childId = edge.getTarget();

            Base base = dataMap.get(childId);
            base.setParentId(parentId);
        }

        Base root = TreeUtils.getTreeRoot(new ArrayList<>(dataMap.values()));
        log.debug("root: {}", objectMapper.writeValueAsString(root));

        setRoot(root);
    }
}
