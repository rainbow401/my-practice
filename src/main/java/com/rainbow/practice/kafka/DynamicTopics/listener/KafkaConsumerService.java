package com.rainbow.practice.kafka.DynamicTopics.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Value("${spring.kafka.topic}")
    public String topic;

    public void setTopic(String newTopic) {
        this.topic = newTopic;
    }

    public String getTopic() {
        return topic;
    }
}