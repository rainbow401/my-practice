package com.rainbow.practice.jdbcTemplate;

import com.rainbow.practice.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/user")
public class JdbcTemplateTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Object insertUser() throws Exception {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }
}
