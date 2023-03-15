package com.rainbow.practice.threadlocal.userinfo.controller;

import com.rainbow.practice.threadlocal.userinfo.Test;
import com.rainbow.practice.threadlocal.userinfo.annotation.InjectUser;
import com.rainbow.practice.threadlocal.userinfo.context.ServiceContext;
import com.rainbow.practice.threadlocal.userinfo.context.UserInfo;
import com.rainbow.practice.threadlocal.userinfo.enums.InjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
@RestController
@RequestMapping("/threadlocal")
public class TestTreadLocalController {

    @Autowired
    private ServiceContext ctx;

    @GetMapping("/test")
    @InjectUser(type = InjectType.AOP)
    public void test() {
        UserInfo userInfo = ctx.currentUserInfo();
        System.out.println("userInfo = " + userInfo);
        Test test = new Test();
        test.test();
    }
}
