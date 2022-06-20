package com.practice.domain.mybatistest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.entity.Test;
import com.practice.entity.TestData;
import com.practice.mapper.TestMapper;
import com.practice.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mybatis/test")
public class TestController {

    @Resource
    private TestMapper testMapper;

    @Resource
    private TestService testService;

    @PostMapping
    public void insert() {

        List<TestData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestData temp = new TestData();
            temp.setId(i);
            temp.setData("test" + i);
            dataList.add(temp);
        }

        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Test temp = new Test();
            temp.setData(dataList);
            tests.add(temp);
        }

        testService.saveBatch(tests);
    }

    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }

    @GetMapping("/list2")
    public List<Test> list2() {
        return testMapper.selectList(new QueryWrapper<>());
    }

    @GetMapping("/list3")
    public List<Test> list3() {
        return testMapper.getList();
    }

    @GetMapping("/mapKey")
    public Map<Long, Test> getIdMapKey() {
        return testMapper.getIdMapKey();
    }
}
