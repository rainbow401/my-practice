package com.practice.domain.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.practice.entity.Test;
import com.practice.entity.TestData;
import com.practice.mapper.TestMapper;
import com.practice.service.TestService;
import com.practice.utils.MybatisBatchUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mybatis")
public class TestController {

    @Resource
    private TestMapper testMapper;

    @Resource
    private TestService testService;

    @Resource
    private MybatisBatchUtils mybatisBatchUtils;

    /**
     * typeHandler test
     */
    @PostMapping
    public void insert() {

        List<TestData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestData temp = new TestData();
            temp.setId(i);
            temp.setName("test" + i);
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

    /**
     * typeHandler test
     */
    @GetMapping("/list")
    public List<Test> list() {
        return testService.list();
    }

    /**
     * typeHandler test
     */
    @GetMapping("/list2")
    public List<Test> list2() {
        return testMapper.selectList(new QueryWrapper<>());
    }

    /**
     * typeHandler test
     */
    @GetMapping("/list3")
    public List<Test> list3() {
        return testMapper.getList();
    }

    /**
     * ‘@MapKey注解’ test
     */
    @GetMapping("/mapKey")
    public Map<Long, Test> getIdMapKey() {
        return testMapper.getIdMapKey();
    }

    /**
     * 测试批处理
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/batchInsert")
    public Integer batch(@RequestParam Integer count) throws Exception {
        List<TestData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestData temp = new TestData();
            temp.setId(i);
            temp.setName("test" + i);
            dataList.add(temp);
        }

        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Test temp = new Test();
            temp.setData(dataList);
            tests.add(temp);
        }
        StopWatch stopWatch = new StopWatch("1");
        stopWatch.start();
        int rowCount = mybatisBatchUtils.batchUpdateOrInsert(tests, TestMapper.class, (item, testMapper) -> testMapper.insert(item));
        stopWatch.stop();
        System.out.println(stopWatch.getId());
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());

        return rowCount;
    }

    /**
     * 测试批处理
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/batchInsert2")
    public boolean batch2(@RequestParam Integer count) throws Exception {
        List<TestData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestData temp = new TestData();
            temp.setId(i);
            temp.setName("test" + i);
            dataList.add(temp);
        }

        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Test temp = new Test();
            temp.setData(dataList);
            tests.add(temp);
        }
        StopWatch stopWatch = new StopWatch("2");
        stopWatch.start();
        boolean result = testService.saveBatch(tests, 500);
        stopWatch.stop();
        System.out.println(stopWatch.getId());
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());

        return result;
    }

    /**
     * 测试批处理
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/batchInsert3")
    public int batch3(@RequestParam Integer count) throws Exception {
        List<TestData> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestData temp = new TestData();
            temp.setId(i);
            temp.setName("test" + i);
            dataList.add(temp);
        }

        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Test temp = new Test();
            temp.setData(dataList);
            tests.add(temp);
        }
        StopWatch stopWatch = new StopWatch("3");
        stopWatch.start();
        int result = 0;
        for (int i = 0; i < tests.size(); i++) {
            result = result + testMapper.insert(tests.get(i));
        }
        stopWatch.stop();
        System.out.println(stopWatch.getId());
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());

        return result;
    }
}