package com.practice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.practice.entity.User;
import com.practice.mapper.UserMapper;
import com.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Object insertUser() throws Exception {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
//        updateWrapper.set(User::getDeleted, 1);

        User user = new User();
        user.setDeleted(1);
//        userMapper.update(user, updateWrapper);
        User user2 = new User();
        user2.setDeleted(1);

//        List<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user2);
//        userService.updateBatchById(users);
        userMapper.update(user, updateWrapper);
        return null;
    }
}
