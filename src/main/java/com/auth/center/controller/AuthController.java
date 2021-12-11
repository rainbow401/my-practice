package com.auth.center.controller;

import com.auth.center.entity.Client;
import com.auth.center.mapper.ClientMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/12/11 3:04 下午
 */
@RestController
@RequestMapping("/client")
public class AuthController {

    @Autowired
    private ClientMapper clientMapper;

    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public List<Client> list() {
        return clientMapper.selectList(new QueryWrapper<>());
    }

//    public String
}
