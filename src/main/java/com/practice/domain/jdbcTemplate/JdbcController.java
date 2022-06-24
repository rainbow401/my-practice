package com.practice.domain.jdbcTemplate;

import com.alibaba.fastjson2.JSON;
import com.practice.entity.Test;
import com.practice.entity.TestData;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {

    @GetMapping("/prepare/search")
    public List search(@RequestParam Integer count) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?useUnicode=true&characterEncoding=utf-8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai", "root", "123456");
        StopWatch stopWatch = new StopWatch("search");
        stopWatch.start();
        int totalCount = 0;
        PreparedStatement ps = connection.prepareStatement("select * from test where id = ?");
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ps.setInt(1, i);
            ResultSet set = ps.executeQuery();
            Test test = new Test();
            while (set.next()) {
                test.setId(set.getLong(1));
                test.setData(JSON.parseArray(set.getString(2), TestData.class));
                test.setTime(set.getDate(3));
                result.add(test);
                totalCount++;
            }
            set.close();
        }

        connection.close();
        ps.close();
        stopWatch.stop();
        System.out.println(stopWatch.getId());
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());
        System.out.println(count);
        System.out.println(totalCount);
        return result;
    }

    @GetMapping("/prepare/search1/")
    public List search1(@RequestParam Integer count) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice?useUnicode=true&characterEncoding=utf-8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai", "root", "123456");
        StopWatch stopWatch = new StopWatch("search");
        stopWatch.start();
        int totalCount = 0;
        PreparedStatement ps = connection.prepareStatement("select * from test where id = ?");
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ps.setInt(1, i);
            ResultSet set = ps.executeQuery();
            Test test = new Test();
            while (set.next()) {
                test.setId(set.getLong(1));
                test.setData(JSON.parseArray(set.getString("data"), TestData.class));
                test.setTime(set.getDate(3));
                result.add(test);
                totalCount++;
                System.out.println();
            }
            set.close();
        }

        connection.close();
        ps.close();
        stopWatch.stop();
        System.out.println(stopWatch.getId());
        System.out.println(stopWatch.getLastTaskInfo().getTimeSeconds());
        System.out.println(count);
        System.out.println(totalCount);
        System.out.println();
        return result;
    }
}
