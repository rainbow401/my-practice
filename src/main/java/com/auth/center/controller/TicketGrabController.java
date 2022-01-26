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
import java.util.List;
import java.util.Set;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/12/11 3:04 下午
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class AuthController {

    @Autowired
    private ClientMapper clientMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String getAuthorizationCode() {
        return "";
    }

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
                Long success = redisTemplate.boundSetOps("tel").add(tel);
                log.info("tel:{} success:{}}", tel,success);
                if(success == 1L) {
                    Object getSuccess = redisTemplate.boundSetOps("ticket").pop();
                    log.info("tel:{} getSuccess:{}}", tel,getSuccess);
                    if(getSuccess != null) {
                        log.info("{}-抢票成功", tel);
                    } else {
                        Long removeSuccess = redisTemplate.boundSetOps("tel").remove(tel);
                        if(removeSuccess != 1L) {
                            log.info("{}移除失败 ", tel);
                        }
                    }
                } else {
                    log.info("{}已存在手机号 ", tel);
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
