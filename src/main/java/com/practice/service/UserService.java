package com.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.practice.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
}
