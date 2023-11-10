package com.rainbow.practice.kafka.DynamicTopics;

import com.rainbow.practice.kafka.DynamicTopics.listener.KafkaConsumerService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TopicSwitchService implements ApplicationContextAware {

    @Value("${spring.kafka.topic}")
    private String currentTopic;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    public void switchTopic(String newTopic) {

        Collection<MessageListenerContainer> listenerContainers = kafkaListenerEndpointRegistry.getListenerContainers();
        MessageListenerContainer kafkaConsumerService1 = kafkaListenerEndpointRegistry.getListenerContainer("kafkaConsumerService");

        // 停止当前监听
        kafkaListenerEndpointRegistry.getListenerContainer("kafkaConsumerService").stop();
        // 动态修改topic
        kafkaConsumerService.setTopic(newTopic);
//        kafkaConsumerService.setTopic(newTopic);

        // 重新启动监听
        kafkaListenerEndpointRegistry.getListenerContainer("kafkaConsumerService").start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }
}
