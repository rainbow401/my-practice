package com.rainbow.practice.freemarker.domain.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaFileScanner {

    public static void main(String[] args) throws ClassNotFoundException {
        String directoryPath = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\model"; // 指定的目录路径
        List<String> javaFilePaths = scanJavaFiles(directoryPath);

        // 输出所有找到的 .java 文件的绝对路径
        for (String javaFilePath : javaFilePaths) {
            System.out.println(javaFilePath);
        }
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
            Class<?> aClass = Class.forName(absolutePath);
            System.out.println("aClass = " + aClass);

            javaFilePaths.add(absolutePath);
        }

        return javaFilePaths;
    }
}
