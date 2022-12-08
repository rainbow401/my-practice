package com.practice.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {

    @Select("select current_value from sequence where sequenceId = #{id}")
    String getId(@Param("Id") String sequenceId);

}
