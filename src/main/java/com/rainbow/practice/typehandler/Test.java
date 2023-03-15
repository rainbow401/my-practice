package com.rainbow.practice.typehandler;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
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
    @TableField(value = "data", typeHandler = JacksonTypeHandler.class, jdbcType = JdbcType.JAVA_OBJECT)
    private List<BelongCompanyVO> data;

    private Date time;

    @TableLogic
    private Integer deleted;

    private static final long serialVersionUID = 1L;
}