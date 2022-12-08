package com.practice.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.auth.mapper.UserMapper;
import com.practice.auth.service.UserService;
import com.practice.auth.entity.User;
import com.practice.auth.entity.dto.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
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
