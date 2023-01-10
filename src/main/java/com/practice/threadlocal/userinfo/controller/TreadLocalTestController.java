package com.practice.threadlocal.userinfo.controller;

import com.practice.Test;
import com.practice.threadlocal.userinfo.annotation.InjectUser;
import com.practice.threadlocal.userinfo.context.ServiceContext;
import com.practice.threadlocal.userinfo.context.UserInfo;
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
public class TreadLocalTestController {

    @Autowired
    private ServiceContext ctx;

    @GetMapping("/test")
    @InjectUser
    public void test() {
        UserInfo userInfo = ctx.currentUserInfo();
        System.out.println("userInfo = " + userInfo);
        Test test = new Test();
        test.test();
    }
}
