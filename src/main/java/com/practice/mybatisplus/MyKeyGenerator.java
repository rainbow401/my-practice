package com.practice.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;

/**
 * 自定义id策略
 */
public class MyKeyGenerator implements IKeyGenerator{

    @Override
    public String executeSql(String incrementerName) {
        return "select fn_nextval('" + incrementerName + "')";
    }

    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }
}
