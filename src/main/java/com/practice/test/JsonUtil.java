package com.practice.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * 输出json数据、日志到文件
 * @author yanzhihao
 */
@Slf4j
public class JsonUtil {

    private static final String SEPARATOR = "time: " + getNow() + "\r\n" + "index: %s \r\n============================================\r\n %s";

    private static final String NOW_TIME = getNow();

    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String jsonRead(File file) {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }

    public static String getJsonStr(String path) throws IOException {
        Resource resource = new PathResource(path);
        return jsonRead(resource.getFile());
    }

    public static JSONObject loadData(String path) throws IOException {
        Resource resource = new PathResource(path);
        String jsonStr = jsonRead(resource.getFile());
        return JSONObject.parseObject(jsonStr);
    }

    public static <T> T getObjectByFile(String path, Class<T> clazz) throws IOException {
        Resource resource = new PathResource(path);
        String jsonStr = jsonRead(resource.getFile());
        return JSONObject.parseObject(jsonStr, clazz);
    }

    public static <T> List<T> getListByFile(String path, Class<T> clazz) throws IOException {
        Resource resource = new PathResource(path);
        boolean exists = resource.exists();
        if (!exists) {
            throw new FileNotFoundException();
        }
        String jsonStr = jsonRead(resource.getFile());
        return JSONArray.parseArray(jsonStr, clazz);
    }

    public static void appendJsonStringToFile(String path, String jsonStr, Integer index) throws IOException {
        PathResource resource = new PathResource(path);
        FileWriter fw = null;
        PrintWriter write = null;
        File file = null;
        try {
            file = resource.getFile();
            fw = new FileWriter(file, true);
            write = new PrintWriter(fw);
            write.println(String.format(SEPARATOR, index, jsonStr));
            write.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert fw != null;
            fw.close();
            assert write != null;
            write.close();
        }
    }

    public static void printStringToFile(String path, String str) throws IOException {
        PathResource resource = new PathResource(path);
        FileWriter fw = null;
        PrintWriter write = null;
        File file = null;
        try {
            file = resource.getFile();
            fw = new FileWriter(file, true);
            write = new PrintWriter(fw);
            write.println(str);
            write.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            assert fw != null;
            fw.close();
            assert write != null;
            write.close();
        }
    }

    public static void printRequestResult(String path, Integer index, String str) throws IOException {
        long start = System.currentTimeMillis();
        printStringToFile(path, "===============================");
        printStringToFile(path, NOW_TIME);
        if (index != null) {
            printStringToFile(path, "index: " + index);
        }
        printStringToFile(path, "===============================");
        printStringToFile(path, str);
        long end = System.currentTimeMillis();
        log.info("打印接口返回值耗时: {}", end - start);
    }


    public static String readFile(String path) throws IOException {
        Resource resource = new PathResource(path);
        boolean exists = resource.exists();
        if (!exists) {
            throw new FileNotFoundException();
        }

        return jsonRead(resource.getFile());
    }
}

