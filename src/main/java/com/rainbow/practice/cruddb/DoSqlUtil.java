package com.rainbow.practice.cruddb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;

/**
 * 获取mysql连接，并执行sql获取返回值
 *
 * @author yanzhihao
 */
public class DoSqlUtil {

    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/blog";
    private final String user = "root";
    private final String pwd = "123456";

    public void getJsonArray() throws Exception {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pwd);
        Statement stet = con.createStatement();

        String sql = "show full columns from user1";
        ResultSet rs = stet.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        System.out.println("转换JSON数据：");
        System.out.println(array.toString());
    }

    public static void main(String[] args) throws Exception {
        DoSqlUtil doSqlUtil= new DoSqlUtil();
        doSqlUtil.getJsonArray();
    }
}
