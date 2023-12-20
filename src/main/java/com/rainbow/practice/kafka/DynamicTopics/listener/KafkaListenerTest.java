package com.rainbow.practice.kafka.DynamicTopics.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: yzh
 * @Date: 2023/11/9
 * @Description:
 */
@Slf4j
@Component
public class KafkaListenerTest {




//    @KafkaListener(topics = "#{@kafkaConsumerService.getTopic()}", id = "kafkaConsumerService")
    public void receiveMessage(String message) {
        // 处理接收到的消息逻辑
        System.out.println("Received message: " + message);
    }


}
