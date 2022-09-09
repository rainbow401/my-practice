package com.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.entity.User;
import com.practice.entity.dto.LoginDTO;
import com.practice.mapper.UserMapper;
import com.practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public boolean login(LoginDTO dto) {
        verifyPassword(dto);

        return true;
    }

    private void verifyPassword(LoginDTO dto) {
        // todo 密码验证
    }
}
