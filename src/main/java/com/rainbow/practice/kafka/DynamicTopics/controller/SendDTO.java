package com.rainbow.practice.kafka.DynamicTopics.controller;

import lombok.Data;

/**
 * @Author: yzh
 * @Date: 2023/11/9
 * @Description:
 */
@Data
public class SendDTO {

    private String topic;

    private String value;
}
