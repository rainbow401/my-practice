package com.practice;

import com.practice.customconfig.CustomConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@MapperScan("com.practice.**.mapper")
@SpringBootApplication
public class PracticeApplication {

    @Autowired
    private CustomConfig customConfig;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);
    }

}
