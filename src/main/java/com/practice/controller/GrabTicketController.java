package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.utils.KafkaUtils;
import com.practice.entity.TicketTel;
import com.practice.mapper.ClientMapper;
import com.practice.mapper.TicketTelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author yanzhihao
 * @Description: 抢票实践
 * @date 2021/12/11 3:04 下午
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class GrabTicketController {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private TicketTelMapper ticketTelMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //余票
    private BoundValueOperations<String, Object> redisTicket;

    //抢到票的手机号Set
    private BoundSetOperations<String, Object> redisTel;

    //重复抢票的手机号
    private final List<String> repeatTel = new ArrayList<>();

    //总计请求数量
    private Integer requestCount = 0;

    //kafka发送数量
    private Integer kafkaSendCount = 0;

    //kafka消费数量
    private Integer kafkaConsumerCount = 0;

    @GetMapping("/init")
    public Object initSet() {
        //绑定redis键值
        redisTicket = redisTemplate.boundValueOps("ticket");
        redisTel = redisTemplate.boundSetOps("tel");

        redisTemplate.delete("ticket");
        redisTemplate.delete("tel");
        ticketTelMapper.delete(new QueryWrapper<>());
        repeatTel.clear();
        requestCount = 0;
        kafkaSendCount = 0;
        kafkaConsumerCount = 0;

        redisTicket.set(5000);

        //检查数据是否正常
        return this.countResult();
    }

    @GetMapping("/ticket")
    public void getTicket(@RequestParam("tel") String tel) {

        log.info("当前时间： {}", System.currentTimeMillis());
        requestCount++;

        redisTicket = redisTemplate.boundValueOps("ticket");
        redisTel = redisTemplate.boundSetOps("tel");

        //首先将手机号放入抢到的手机号Set里
        Long success = redisTel.add(tel);
        log.info("tel:{} success:{}}", tel, success);

        //如果success 返回 0 则表示手机号已存在
        if (success == null || success == 1L) {
            Long ticketCount = redisTicket.decrement();
            log.info("tel:{} ticketCount:{}", tel, ticketCount);
            //判断票是否已经抢完
            if (ticketCount != null && ticketCount >= 0L) {
                log.info("{}-抢票成功", tel);
                //放到kafka队列中
                //防止kafka丢消息，做如下记录后还要在kafka配置增加重试次数
                ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("tel", tel);
                future.addCallback(
                        result -> {
                            this.kafkaSendCount++;
                            KafkaUtils.sendSuccessCallBack(result);
                        },
                        KafkaUtils::sendFailedCallBack
                );
            } else {
                //票已抢完 需要将存放已抢完手机号的Set里的手机号删除
//                Long removeSuccess = redisTel.remove(tel);
//                if (removeSuccess == null || removeSuccess != 1L) {
//                    log.info("{}移除失败 ", tel);
//                    //TODO 在另外的地方存储
//                }
                log.info("tel-{} 票已抢完", tel);
            }
        } else {
            repeatTel.add(tel);
            log.info("{} 已抢到票 ", tel);
        }
    }

    @KafkaListener(topics = {"tel"})
    public void consumer(String tel) {
        log.info("消费：{} ", tel);
        TicketTel ticketTel = new TicketTel();
        ticketTel.setTel(tel);
        ticketTel.setStatus(1);
        ticketTelMapper.insert(ticketTel);
        kafkaConsumerCount++;
    }

    @GetMapping("/ticket/count")
    public Object checkTicket() {
        return this.countResult();
    }


    public Map<String, Object> countResult() {

        redisTicket = redisTemplate.boundValueOps("ticket");
        redisTel = redisTemplate.boundSetOps("tel");

        Map<String, Object> result = new TreeMap<>();
        List<TicketTel> list = ticketTelMapper.selectList(new QueryWrapper<>());

        result.put("redis tel count", redisTel.members().size());
        result.put("redis remain ticket count", redisTicket.get());

        result.put("mysql grab success tel count", list.size());

        result.put("kafka send count", kafkaSendCount);
        result.put("kafka consumer count", kafkaConsumerCount);

        result.put("request repeat tel count", repeatTel.size());
        result.put("request all count", requestCount);

//        log.info("repeat request tel {}", repeatTel);
//        log.info("redis tel {}", redisTel.members());
//        log.info("mysql tel {}", list);

        return result;
    }
}
