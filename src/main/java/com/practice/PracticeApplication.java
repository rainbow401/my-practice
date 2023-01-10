package com.practice;

import com.practice.customconfig.CustomConfig;
import com.practice.threadlocal.userinfo.config.InterceptorConfiguration;
import com.practice.threadlocal.userinfo.context.ServiceContext;
import com.practice.threadlocal.userinfo.context.ServiceContextInternal;
import com.practice.threadlocal.userinfo.context.UserInfoService;
import com.practice.threadlocal.userinfo.controller.TreadLocalTestController;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;

@Slf4j
@MapperScan("com.practice.**.mapper")
@SpringBootApplication
public class PracticeApplication {

    @Autowired
    private CustomConfig customConfig;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);
        InterceptorConfiguration bean = ctx.getBean(InterceptorConfiguration.class);
        System.out.println("bean = " + bean);
    }

}
