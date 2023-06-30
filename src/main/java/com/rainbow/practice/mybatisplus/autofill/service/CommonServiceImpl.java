package com.rainbow.practice.mybatisplus.autofill.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainbow.practice.mybatisplus.autofill.mapper.CommonMapper;
import com.rainbow.practice.mybatisplus.autofill.model.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@Slf4j
@Service
public class CommonServiceImpl extends ServiceImpl<CommonMapper, Demo> implements CommonService {


    @Override
    public void test() {
        log.info("test");
        ArrayList<Demo> objects = new ArrayList<>();
        objects.add(new Demo(1L, "4"));
        objects.add(new Demo(2L, "5"));

        this.removeById(1L);
    }
}
