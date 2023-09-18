package com.rainbow.practice.process.parse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainbow.practice.process.model.Base;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yzh
 * @Date: 2023/9/18
 * @Description:
 */
public class Parse {

    private static final String PATH = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\process\\testdata\\data.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        String jsonStr = FileUtils.readFileToString(
                new File(PATH),
                StandardCharsets.UTF_8);
        System.out.println("s = " + jsonStr);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 将JSON数据转换为GraphObject数组
        Base[] bases = objectMapper.readValue(new File(PATH), Base[].class);
        System.out.println("bases = " + bases);

    }
}
