package com.practice;

import com.practice.customconfig.CustomConfig;
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
@MapperScan("com.practice.*")
@SpringBootApplication
public class PracticeApplication {

    @Autowired
    private CustomConfig customConfig;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);
        ConfigurableEnvironment environment = ctx.getEnvironment();
        Iterator<PropertySource<?>> iterator = environment.getPropertySources().stream().iterator();
        while (iterator.hasNext()) {
            PropertySource<?> next = iterator.next();
            Object demo = next.getProperty("demo");
            System.out.println("demo = " + demo);
        }
        CustomConfig customConfig = (CustomConfig) ctx.getBean("customConfig");
        System.out.println("customConfig = " + customConfig);
    }

}
