package com.practice.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @Description:
 * @Author: yanzhihao
 * @Date: 2022/2/8 3:47 下午
 */
@Slf4j
public class KafkaUtils {

    public static void sendSuccessCallBack(SendResult<String, String> result) {
        if( result == null || result.getRecordMetadata() == null) {
            log.info("发送成功，但kafka出现异常");
        } else {
            log.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition());
        }
    }

    public static void sendFailedCallBack(Throwable ex) {
        //TODO 做持久化处理
        log.error("生产者发送消失败，原因：{}", ex.getMessage());
    }
}
