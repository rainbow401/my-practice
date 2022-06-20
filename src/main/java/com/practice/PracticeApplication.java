package com.practice;

import com.practice.vo.AccessTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@MapperScan("com.practice.mapper")
@Slf4j
public class PracticeApplication {

    public static void main(String[] args) {
        System.out.println("--------------------------");
        for (String e : args) {
            System.out.println("args: " + e);
        }
        System.out.println("--------------------------");
        args = new String[] {"--server.port=9090"};
        SpringApplication.run(PracticeApplication.class, args);
    }
}
