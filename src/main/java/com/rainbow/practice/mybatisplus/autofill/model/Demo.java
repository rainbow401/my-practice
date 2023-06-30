package com.rainbow.practice.mybatisplus.autofill.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@Data
@TableName("user")
public class Demo {

    @TableId
    private Long id;

    @TableField(value = "loginName",fill = FieldFill.INSERT_UPDATE)
    private String loginName;

    public Demo(Long id) {
        this.id = id;
    }

    public Demo() {
    }

    public Demo(Long id, String loginName) {
        this.id = id;
        this.loginName = loginName;
    }

    @TableField(value = "deletedAt",fill = FieldFill.INSERT_UPDATE)
    private Date deletedAt;

    @TableLogic(value = "0",delval = "1")
    private Integer deleted;
}
