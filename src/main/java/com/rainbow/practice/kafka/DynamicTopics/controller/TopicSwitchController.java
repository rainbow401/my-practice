package com.rainbow.practice.kafka.DynamicTopics.controller;

import com.rainbow.practice.kafka.DynamicTopics.TopicSwitchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yzh
 * @Date: 2023/11/10
 * @Description:
 */
@Slf4j
@RequestMapping("/TopicSwitch")
@RestController
@RequiredArgsConstructor
public class TopicSwitchController {

    private final TopicSwitchService topicSwitchService;

    @PostMapping("/{topic}")
    public void switchTopic(@PathVariable("topic") String topic) {
        log.info("switchTopic params: [{}]", topic);
        topicSwitchService.switchTopic(topic);
    }
}
