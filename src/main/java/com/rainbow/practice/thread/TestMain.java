package com.rainbow.practice.thread;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yzh
 * @Date: 2023/9/22
 * @Description:
 */
@Slf4j
public class TestMain {

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            int finalI = i;
            new Thread(() -> {
                HttpUtil.get("localhost:9090/test/thread/addThread?num=" + finalI);
                System.out.println("finalI = " + finalI);
            }).start();
        }

        System.out.println("args = " + args);
        Thread.yield();
    }
}
