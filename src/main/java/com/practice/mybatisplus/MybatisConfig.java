package com.practice.mybatisplus;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MybatisConfig {

    @Bean
    public IKeyGenerator keyGenerator() {
        return new MyKeyGenerator();
    }
}
