package com.rainbow.practice.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainbow.practice.mybatisplus.model.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@Mapper
public interface CommonMapper extends BaseMapper<Demo> {

}
