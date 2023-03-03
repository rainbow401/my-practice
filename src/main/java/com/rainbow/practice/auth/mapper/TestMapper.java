package com.rainbow.practice.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.practice.auth.entity.Test;
import com.rainbow.practice.auth.entity.TestData;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author lenovo
* @description 针对表【test】的数据库操作Mapper
* @createDate 2022-06-20 09:02:07
* @Entity com.practice.entity.Test
*/
@Mapper
public interface TestMapper extends BaseMapper<Test> {


    List<Test> getList();

    @MapKey("id")
    Map<Long, Test> getIdMapKey();

    int updateData(@Param("data") List<TestData> data);
}
