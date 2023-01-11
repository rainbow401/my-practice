package com.practice.classloader;

import com.practice.PracticeApplication;
import com.practice.file.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<ClassLoader> list = new ArrayList<>();
        ClassLoader classLoader1 = String.class.getClassLoader();
        ClassLoader classLoader2 = FileUtils.class.getClassLoader();
        ClassLoader classLoader3 = Integer.class.getClassLoader();
        ClassLoader classLoader4 = int.class.getClassLoader();
        ClassLoader classLoader5 = List.class.getClassLoader();
        ClassLoader classLoader6 = PracticeApplication.class.getClassLoader();
        list.add(classLoader1);
        list.add(classLoader2);
        list.add(classLoader3);
        list.add(classLoader4);
        list.add(classLoader5);
        list.add(classLoader6);

        System.out.println(list);
    }
}
