package com.practice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan
@Slf4j
public class AuthorizeCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizeCenterApplication.class, args);
    }

}
