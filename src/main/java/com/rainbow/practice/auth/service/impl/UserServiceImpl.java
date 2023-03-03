package com.rainbow.practice.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.practice.auth.mapper.UserMapper;
import com.rainbow.practice.auth.service.UserService;
import com.rainbow.practice.auth.entity.User;
import com.rainbow.practice.auth.entity.dto.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean login(LoginDTO dto) {
        verifyPassword(dto);

        return true;
    }

    private void verifyPassword(LoginDTO dto) {
        // todo 密码验证
    }
}
