package com.rainbow.practice.threadlocal.userinfo.config;

import com.rainbow.practice.threadlocal.userinfo.context.impl.ServiceContextImpl;
import com.rainbow.practice.threadlocal.userinfo.interceptor.ServiceContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Bean
    public ServiceContextImpl serviceContext() {
        return new ServiceContextImpl();
    }

    @Bean
    public ServiceContextInterceptor serviceContextInterceptor() {
        return new ServiceContextInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceContextInterceptor());
    }
}
