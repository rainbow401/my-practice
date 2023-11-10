package com.rainbow.practice.process.define.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.rainbow.practice.process.define.element.Base;
import com.rainbow.practice.process.define.element.BaseType;
import com.rainbow.practice.process.define.element.Edge;
import com.rainbow.practice.process.define.validation.EdgeValidation;
import com.rainbow.practice.process.define.validation.EventValidation;
import com.rainbow.practice.process.define.validation.NodeTreeValidation;
import com.rainbow.practice.process.define.validation.ProcessValidation;
import com.rainbow.practice.tree.CommonNode;
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
public class ProcessDefineParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final ObjectWriter prettyPrinter = objectMapper.writerWithDefaultPrettyPrinter();

    private static final HashMap<String, NodeConvert> convertMap = new HashMap<>();

    private static final List<ProcessValidation> processValidations = new ArrayList<>();

    private static final List<NodeTreeValidation> nodeTreeValidations = new ArrayList<>();


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

        processValidations.add(new EdgeValidation());
        processValidations.add(new EventValidation());
    }

    public static ProcessDefineParser createParser() {
        return new ProcessDefineParser();
    }

    public List<Base> parseJson(String jsonStr) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        List<Base> baseList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            log.debug("node: {}", node);

            JsonNode shapeJson = node.get("shape");
            if (shapeJson == null) {
                throw new IllegalStateException("field 'shape' is missing");
            }

            String shape = shapeJson.asText();
            if (StringUtils.isBlank(shape)) {
                log.error("shape is blank");
                continue;
            }

            NodeConvert convert = convertMap.get(shape);
            if (convert == null) {
                log.error("not found suitable convert");
                continue;
            }

            Base base = convert.parse(node);
            baseList.add(base);
        }

        if (baseList.isEmpty()) {
            return Collections.emptyList();
        }

        for (ProcessValidation validation : processValidations) {
            validation.valid(baseList);
        }

        log.debug("baseList: {}", baseList);

        return baseList;
    }

    public Base parseTree(List<Base> data) throws JsonProcessingException {
        if (data.isEmpty()) {
            throw new IllegalStateException("param is empty");
        }

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
            String source = edge.getSource();
            String target = edge.getTarget();

            Base base = dataMap.get(target);
            base.setConditions(edge.getConditions());
            base.setParentId(source);
        }

        Base root = TreeUtils.getTreeRoot(dataMap.values());
        for (NodeTreeValidation validation : nodeTreeValidations) {
            validation.valid(root);
        }

        log.debug("root: {}", objectMapper.writeValueAsString(root));

        return root;
    }

    public Base parseDefine(String jsonStr) throws JsonProcessingException {
        List<Base> bases = parseJson(jsonStr);
        return parseTree(bases);
    }

    public static void main(String[] args) {
        CommonNode root = new CommonNode();
        root.setId(1);
        root.setParentId(null);

        CommonNode node1 = new CommonNode();
        node1.setId(2);

        CommonNode node2 = new CommonNode();
        node2.setId(3);

        root.setChild(Collections.singletonList(node1));
        node1.setChild(Collections.singletonList(node2));
        node2.setChild(Collections.singletonList(node1));

        System.out.println("root = " + 1);
    }
}
