package com.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.practice.entity.typehandler.JsonArrayToUserListHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @TableName test
 */
@Data
@TableName(value = "test", autoResultMap = true)
public class Test implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "data", typeHandler = JsonArrayToUserListHandler.class)
    private List<TestData> data;

    private static final long serialVersionUID = 1L;
}