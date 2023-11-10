package com.rainbow.practice.kafka.DynamicTopics;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author: yzh
 * @Date: 2023/11/9
 * @Description:
 */
@Slf4j
@Component
public class TopicFactory {

    public List<String> topics = Collections.singletonList("topic1");

    public List<String> random() {
        String uuid = UUID.randomUUID().toString();
        log.info("random uuid {}", uuid);
        return Collections.singletonList(uuid);
    }



    public void changeTopicValue(List<String> topics) {
        this.topics = topics;
        log.info("changeTopicValue params: [{}]\n success", topics);
    }

    public String[] getTopics() {
        return topics.toArray(new String[0]);
    }
}
