package com.rainbow.practice.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.practice.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
