package com.rainbow.practice.kafka.DynamicTopics.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.config.KafkaListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: yzh
 * @Date: 2023/11/9
 * @Description:
 */
@Component
@DependsOn(value = {"kafkaListenerTest"})
public class ApplicationTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, KafkaListenerEndpointRegistrar> beansOfType = applicationContext.getBeansOfType(KafkaListenerEndpointRegistrar.class);
        System.out.println("beansOfType = " + beansOfType);
    }
}
