package com.rainbow.practice.kafka.DynamicTopics.controller;

import com.rainbow.practice.kafka.DynamicTopics.TopicFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: yzh
 * @Date: 2023/11/9
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class TestKafkaController {

    private final TopicFactory topicFactory;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/changeTopic")
    public void changeValue(@RequestBody List<String> topicList) {
        log.info("changeValue params: [{}]", topicList);
        topicFactory.changeTopicValue(topicList);
    }

    @PostMapping("/send")
    public void send(@RequestBody SendDTO dto) {
        log.info("send params: [{}]", dto);
        kafkaTemplate.send(dto.getTopic(), dto.getValue());
    }
}
