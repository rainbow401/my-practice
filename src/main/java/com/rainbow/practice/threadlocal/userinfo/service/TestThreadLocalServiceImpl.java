package com.rainbow.practice.threadlocal.userinfo.service;

import org.springframework.stereotype.Service;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
@Service
public class TestThreadLocalServiceImpl implements TestThreadLocalService {
    @Override
    public void say() {
        System.out.println("TestServiceImpl");
    }
}
