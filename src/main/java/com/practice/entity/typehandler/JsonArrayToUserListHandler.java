package com.practice.entity.typehandler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson2.JSON;
import com.practice.entity.TestData;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonArrayToUserListHandler extends BaseTypeHandler<List<TestData>> {

    // 执行插入数据方法 且 实体属性不为空时
    // 属性为空的时候也没必要进行相应的处理
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<TestData> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(JSON.toJSON(parameter)));
    }

    // 根据列名获取数据
    // 数据的值可能为空
    @Override
    public List<TestData> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String data = rs.getString(columnName);
        if (data != null) {
            return JSON.parseArray(data, TestData.class);
        }
        return null;
    }

    // 根据列索引获取数据
    // 数据的值可能为空
    @Override
    public List<TestData> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String data = rs.getString(columnIndex);
        if (data != null) {
            return JSON.parseArray(data, TestData.class);
        }
        return null;
    }

    // ???
    @Override
    public List<TestData> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String data = cs.getString(columnIndex);
        if (data != null) {
            return JSON.parseArray(data, TestData.class);
        }
        return null;
    }
}
