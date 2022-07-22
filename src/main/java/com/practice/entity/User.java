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

    private String userName;
}
