package com.rainbow.practice.freemarker.domain.service;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: yzh
 * @Date: 2024/3/19
 * @Description:
 */
public class MybatisPlusTemplateGenerator {
    public static void main(String[] args) throws ClassNotFoundException, TemplateException, IOException {
        String serviceTemplatePath = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\freemarker\\domain\\service\\template";
        String outputServicePath = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\freemarker\\domain\\service\\result\\";

        // 模拟从数据库获取表信息
        List<TableInfo> tableInfoList = getTableInfoList();
        String basePackage = "com.rainbow.practice.freemarker.domain.service.result";
        generateServiceCode(serviceTemplatePath, outputServicePath + "service\\", tableInfoList, basePackage);
        generateServiceImplCode(serviceTemplatePath, outputServicePath + "service\\impl\\", tableInfoList, basePackage);
    }

    public static void generateServiceCode(String templatePath, String outputPath, List<TableInfo> tableInfoList, String basePackage) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            Template template = cfg.getTemplate("service.ftl");

            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> input = new HashMap<>();
                input.put("basePackage", basePackage);
                input.put("className", tableInfo.getClassName());
                input.put("imports", tableInfo.getImports());

                Writer fileWriter = new FileWriter(new File(outputPath + tableInfo.getClassName() + "Service.java"));
                template.process(input, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            }

            System.out.println("Service code generated successfully!");

    }

    public static void generateServiceImplCode(String templatePath, String outputPath, List<TableInfo> tableInfoList, String basePackage) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = cfg.getTemplate("serviceImpl.ftl");

        for (TableInfo tableInfo : tableInfoList) {
            Map<String, Object> input = new HashMap<>();
            input.put("basePackage", basePackage);
            input.put("className", tableInfo.getClassName());
            input.put("fields", tableInfo.getFields());
            input.put("imports", tableInfo.getImports());

            Writer fileWriter = new FileWriter(new File(outputPath + tableInfo.getClassName() + "ServiceImpl.java"));
            template.process(input, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        }

        System.out.println("ServiceImpl code generated successfully!");
    }

    // 模拟获取数据库表信息的方法
    private static List<TableInfo> getTableInfoList() throws ClassNotFoundException {
        // 实现获取数据库表信息的逻辑，例如连接数据库查询表结构，并将其映射为TableInfo对象列表
        // 这里为了演示，直接返回一个空列表
        List<TableInfo> tableInfoList = new ArrayList<>();

        List<String> classPathList = scanJavaFiles("C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\freemarker\\domain\\service\\result\\entity");
        for (String classPath : classPathList) {
            TableInfo tableInfo = new TableInfo();

            Class<?> clazz = Class.forName(classPath);

            int lastIndexOf = classPath.lastIndexOf(".");

            tableInfo.setClassName(classPath.substring(lastIndexOf + 1));

            Field[] fields = clazz.getDeclaredFields();
            List<String> fieldsList = Arrays.stream(fields).filter(e-> !java.lang.reflect.Modifier.isStatic(e.getModifiers())).map(Field::getName).collect(Collectors.toList());

            tableInfo.setFields(fieldsList);

            tableInfoList.add(tableInfo);
        }

        return tableInfoList;
    }

    @Data
    public static class TableInfo {
        private String className;
        private List<String> fields;
        private List<String> imports;
        // other fields

        // 省略构造函数和getter/setter方法
    }

    public static List<String> scanJavaFiles(String directoryPath) throws ClassNotFoundException {
        List<String> javaFilePaths = new ArrayList<>();
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory path: " + directoryPath);
            return javaFilePaths;
        }

        File[] files = directory.listFiles((dir, name) -> name.endsWith(".java"));
        if (files == null) {
            System.err.println("No Java files found in directory: " + directoryPath);
            return javaFilePaths;
        }

        for (File file : files) {
            String absolutePath = file.getAbsolutePath();
            int lastIndexOf = absolutePath.lastIndexOf("com\\");
            absolutePath = absolutePath.substring(lastIndexOf);
            absolutePath = absolutePath.replace("\\", ".");
            absolutePath = absolutePath.replace(".java", "");

            javaFilePaths.add(absolutePath);
        }

        return javaFilePaths;
    }
}
