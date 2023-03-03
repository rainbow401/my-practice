package com.rainbow.practice.yaml;

import com.rainbow.practice.file.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.Map;

/**
 * @author: YanZhiHao
 * @date: 2023/3/3
 * @description:
 */
public class YamlUtils {

    public static Map<String, Object> loadYaml(String filePath){
        Yaml yaml = new Yaml();
        File file = new File(filePath);
        return yaml.load(FileUtils.readFile(file));
    }
}
