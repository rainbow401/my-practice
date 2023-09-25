package com.rainbow.practice.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yzh
 * @Date: 2023/9/22
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/test/thread")
public class TestController {


    @GetMapping("/addThread")
    public void test(int num) throws InterruptedException {
        log.info("{} 接受到请求:num={}", Thread.currentThread().getName(), num);
        TimeUnit.HOURS.sleep(1);
    }
}
