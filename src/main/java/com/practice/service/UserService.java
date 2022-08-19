package com.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.entity.User;
import com.practice.entity.dto.LoginDTO;

public interface UserService extends IService<User> {

    boolean login(LoginDTO dto);

}
