package com.rainbow.practice.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.rainbow.practice.excel.ExcelUtils;
import com.rainbow.practice.excel.easyexcel.data.User;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/12/18
 * @Description:
 */
@Slf4j
public class TestRead {

    public static void main(String[] args) throws Exception {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "C:\\project\\my-practice\\src\\main\\java\\com\\rainbow\\practice\\excel\\easyexcel\\files\\result.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        List<User> result = new ArrayList<>();
        EasyExcel.read(fileName, User.class, new PageReadListener<User>(dataList -> {
            for (User demoData : dataList) {
                log.info("读取到一条数据{}", demoData.toString());
                result.add(demoData);
            }
        })).sheet().doRead();

        System.out.println("result = " + result);
    }
}
