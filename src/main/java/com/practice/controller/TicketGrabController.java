package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.entity.TicketTel;
import com.practice.mapper.ClientMapper;
import com.practice.mapper.TicketTelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author yanzhihao
 * @Description: 抢票实践
 * @date 2021/12/11 3:04 下午
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class TicketGrabController {


    private List<String> repeatTel = new ArrayList<>();

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private TicketTelMapper ticketTelMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/init")
    public Object initSet() {

        Long start = System.currentTimeMillis();
        redisTemplate.delete("ticket");
        redisTemplate.delete("tel");
        ticketTelMapper.delete(new QueryWrapper<>());
        repeatTel.clear();

        Set<String> tel = new HashSet<>();
        for (int i = 0; i < 5000; i++) {
            Long result = redisTemplate.boundSetOps("ticket").add(i);
            log.info("add i: {}, redisTemplate result: {}", i, result);
        }

        Long end = System.currentTimeMillis();
        log.info("耗时：{}", end - start);

        return redisTemplate.boundSetOps("ticket").members();
    }

    @GetMapping("/ticket")
    public Object getTicket(@RequestParam("tel") String tel) {
        log.info("======时间： {}", System.currentTimeMillis());

        //首先将手机号放入抢到的手机号Set里
        Long success = redisTemplate.boundSetOps("tel").add(tel);
        log.info("tel:{} success:{}}", tel, success);

        //如果success 返回 0 则表示手机号已存在
        if (success == null || success == 1L) {

            Object getSuccess = redisTemplate.boundSetOps("ticket").pop();
            log.info("tel:{} getSuccess:{}}", tel, getSuccess);

            //如果 getSuccess 为 null 则表示票已抢完
            if (getSuccess != null) {
                log.info("{}-抢票成功", tel);
                //放到kafka队列中
                //防止kafka丢消息，做如下记录后还要在kafka配置增加重试次数
                ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("ticket", tel);
                future.addCallback(
                        result -> log.info("生产者成功发送消息到topic:{} partition:{}的消息", result.getRecordMetadata().topic(), result.getRecordMetadata().partition()),
                        ex -> log.error("生产者发送消失败，原因：{}", ex.getMessage())
                );
            } else {
                //票已抢完 需要将存放已抢完手机号的Set里的手机号删除
                Long removeSuccess = redisTemplate.boundSetOps("tel").remove(tel);
                if (removeSuccess == null || removeSuccess != 1L) {
                    log.info("{}移除失败 ", tel);
                    //TODO 在另外的地方存储
                }
            }
        } else {
            repeatTel.add(tel);
            log.info("{} 已抢到票 ", tel);
        }
        return "";
    }

    @KafkaListener(topics = {"ticket"})
    public void consumer(String tel) {
        log.info("消费：{} ", tel);
        TicketTel ticketTel = new TicketTel();
        ticketTel.setTel(tel);
        ticketTel.setStatus(1);
        ticketTelMapper.insert(ticketTel);
    }

    @GetMapping("/ticket/count")
    public Object checkTicket() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tel", redisTemplate.boundSetOps("tel").members());
        result.put("telCount", redisTemplate.boundSetOps("tel").members().size());
        result.put("ticketCount", redisTemplate.boundSetOps("ticket").members().size());
        result.put("repeatTel", repeatTel);
        result.put("repeatTelCount", repeatTel.size());
        result.put("ticketTel", ticketTelMapper.selectList(new QueryWrapper<>()).size());
        return result;
    }

}
