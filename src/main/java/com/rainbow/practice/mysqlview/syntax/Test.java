package com.rainbow.practice.mysqlview.syntax;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;

import java.util.List;

/**
 * @author yanzhihao
 * @since 2023/5/8
 */
public class Test {

    public static void main(String[] args) {
        String sql = "update table user set name = '1' where 1=1; \n" +
                "update table user set name = '1' where 1=;";
//        format(sql, DbType.mysql);

        List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
        for (SQLStatement e : sqlStatements) {
            System.out.println(e);
        }
    }

    public static void format(String sql, DbType dbType) {
        String sqlFormat = SQLUtils.format(sql, dbType);
        System.out.println(sqlFormat);
        if (sql.equals(sqlFormat)) {
            throw new RuntimeException("SQL格式错误");
        }
    }

}
