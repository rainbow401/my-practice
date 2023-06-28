package com.rainbow.practice.mapstruct;

import com.rainbow.practice.mapstruct.model.Demo1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yanzhihao
 * @since 2023/6/28
 */
@SpringBootTest
class Demo {

    @Test
    void contextLoads() {
        Demo1 demo1 = new Demo1();
        demo1.setUsername("sdsada");
        Convert.CONVERT.convert(demo1);
    }

}