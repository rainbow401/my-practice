package com.rainbow.practice.springlearn.ioc.aware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwareConfiguration {

    @Bean
    public AwareTestBean awareTestBean() {
        return new AwareTestBean();
    }
}
