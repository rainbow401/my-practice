package com.practice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.entity.User;
import com.practice.mapper.UserMapper;
import com.practice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
