package com.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: yanzhihao
 * @Date 2022/2/10 8:50 上午
 */
@RequestMapping("/redis/test")
@RestController
@Slf4j
public class RedisController {

    private List<Long> decrementStatics = new ArrayList<>();

    private List<Long> popStatics = new ArrayList<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/test")
    public void test() {
        Long startTime = System.currentTimeMillis();
        redisTemplate.opsForValue().decrement("value");
        Long endTime = System.currentTimeMillis();

        Long startTime2 = System.currentTimeMillis();
        redisTemplate.opsForSet().pop("set");
        Long endTime2 = System.currentTimeMillis();

        log.info("\n" +
                 "decrement time : {} \n" +
                 "pop time: {}", endTime - startTime, endTime2 - startTime2);

        synchronized (this) {
            decrementStatics.add(endTime - startTime);
            popStatics.add(endTime2 - startTime2);
        }

        log.info("\n" +
                "decrement count : {} \n" +
                "pop count: {}", decrementStatics.size(), popStatics.size());
    }

    @RequestMapping("/init")
    public Object init() {
        for (int i = 0; i < 20000; i++) {
            redisTemplate.opsForSet().add("set", i);
        }

        redisTemplate.opsForValue().set("value", 5000);

        popStatics.clear();
        decrementStatics.clear();
        return null;
    }

    @RequestMapping("/check")
    public Object check() {
        Map<String, Object> result = new TreeMap<>();
        result.put("set", redisTemplate.opsForSet().members("set").size());
        result.put("value", redisTemplate.opsForValue().get("value"));
        result.put("setCount", popStatics.size());
        result.put("valueCount", decrementStatics.size());

        Long allPopTime = 0L;
        Long allDecrementTime = 0L;
        for(int i = 0; i < popStatics.size(); i++) {
            allPopTime = allPopTime + popStatics.get(i);
        }

        for(int i = 0; i < decrementStatics.size(); i++) {
            allDecrementTime = allDecrementTime + decrementStatics.get(i);
        }

        Object o = allPopTime == 0L ? 1==1 : result.put("avgSetTime", allDecrementTime / decrementStatics.size());
        Object o1 = allPopTime == 0L ? 1==1 : result.put("avgPopTime", allPopTime / popStatics.size());

        return result;
    }
}
