package com.practice.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.practice.typehandler.JsonArrayToUserListHandler;

import java.util.List;

@TableName("test")
public class Test2 {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(typeHandler = JsonArrayToUserListHandler.class)
    private List<TestData> data;

    private static final long serialVersionUID = 1L;
}
