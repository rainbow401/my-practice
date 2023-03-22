package com.rainbow.practice;

import org.springframework.stereotype.Service;

/**
 * @Author: YanZhiHao
 * @Date: 2023/3/15
 * @Description:
 */
@Service
public class TestService {

    public TestService() {
        System.out.println("TestService 注入了 ---------------");
    }
}
