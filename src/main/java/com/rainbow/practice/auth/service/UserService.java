package com.rainbow.practice.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rainbow.practice.auth.entity.User;
import com.rainbow.practice.auth.entity.dto.LoginDTO;

public interface UserService extends IService<User> {

    boolean login(LoginDTO dto);

}
