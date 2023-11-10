package com.rainbow.practice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@Slf4j
@MapperScan("com.rainbow.practice.**.mapper")
@SpringBootApplication
@EnableKafka
public class PracticeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PracticeApplication.class, args);
    }

}
