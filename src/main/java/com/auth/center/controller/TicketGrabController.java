package com.auth.center.controller;

import com.auth.center.entity.Client;
import com.auth.center.mapper.ClientMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/12/11 3:04 下午
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class TicketGrabController {

    @Autowired
    private ClientMapper clientMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/init")
    public Object initSet() {

        Long start = System.currentTimeMillis();

        Set<String> tel = new HashSet<>();
        for (int i = 0; i < 5000; i++) {
            Long result = redisTemplate.boundSetOps("ticket").add(i);
            log.info("add i: {}, redisTemplate result: {}", i, result);
        }

        Long end = System.currentTimeMillis();
        log.info("耗时：{}", end - start);
        return redisTemplate.boundSetOps("ticket").members();
    }

    @GetMapping("ticket")
    public Object getTicket() {
        GetTicket getTicket = new GetTicket();
        Thread getTicketThread = new Thread(getTicket);
        getTicketThread.start();
        return redisTemplate.boundSetOps("tel").members();
    }

    public class GetTicket implements Runnable {
        @Override
        public void run() {
            int startTel = 1000;
            for(int tel = startTel; tel < startTel + 5000; tel++) {
                //首先将手机号放入抢到的手机号Set里
                Long success = redisTemplate.boundSetOps("tel").add(tel);
                log.info("tel:{} success:{}}", tel,success);
                //如果success 返回 0 则表示手机号已存在
                if(success == null ||success == 1L) {
                    Object getSuccess = redisTemplate.boundSetOps("ticket").pop();
                    log.info("tel:{} getSuccess:{}}", tel,getSuccess);

                    //如果 getSuccess 为 null 则表示票已抢完
                    if(getSuccess != null) {
                        log.info("{}-抢票成功", tel);
                    } else {
                        //票已抢完 需要将存放已抢完手机号的Set里的手机号删除
                        Long removeSuccess = redisTemplate.boundSetOps("tel").remove(tel);
                        if(removeSuccess == null || removeSuccess != 1L) {
                            log.info("{}移除失败 ", tel);
                            //在另外的地方存储
                        }
                    }
                } else {
                    log.info("{} 已抢到票 ", tel);
                }
            }
        }
    }

    @GetMapping("/check")
    public Object checkTicket() {
//        log.info("tel: {}", redisTemplate.boundSetOps("tel").members());
//        log.info("telCount: {}", redisTemplate.boundSetOps("tel").members().size());
//        log.info("ticketCount: {}", redisTemplate.boundSetOps("ticket").members().size());
        HashMap<String, Object> result = new HashMap<>();
        result.put("tel", redisTemplate.boundSetOps("tel").members());
        result.put("telCount", redisTemplate.boundSetOps("tel").members().size());
        result.put("ticketCount", redisTemplate.boundSetOps("ticket").members().size());
        return result;
    }

}
