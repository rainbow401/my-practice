package com.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practice.entity.Test;
import com.practice.service.TestService;
import com.practice.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【test】的数据库操作Service实现
* @createDate 2022-06-20 09:02:07
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService{


}
