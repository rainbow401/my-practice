package com.practice.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.practice.entity.typehandler.JsonArrayToListTypeHandler;
import com.practice.entity.typehandler.JsonArrayToUserListHandler;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName test
 */
@Data
@TableName(autoResultMap = true)
public class Test implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "data", typeHandler = JsonArrayToUserListHandler.class, jdbcType = JdbcType.JAVA_OBJECT)
    private List<TestData> data;

    private Date time;

    @TableLogic
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}