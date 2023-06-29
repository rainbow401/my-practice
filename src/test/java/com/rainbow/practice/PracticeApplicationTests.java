package com.rainbow.practice;

import com.rainbow.practice.mybatisplus.service.CommonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeApplicationTests {

    @Autowired
    private CommonService commonService;

    @Test
    void contextLoads() {
        commonService.test();
    }

}
