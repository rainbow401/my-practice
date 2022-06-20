package com.practice.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDateTime;

@Data
@KeySequence("PS")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("loginName")
    private String LoginName;

    @TableField(value = "deletedAt",fill = FieldFill.UPDATE)
    private LocalDateTime deletedAt;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
