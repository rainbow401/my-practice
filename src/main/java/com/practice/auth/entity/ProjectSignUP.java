package com.practice.auth.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("projectsignup")
@Data
@KeySequence("")
public class ProjectSignUP {

    @TableId(type = IdType.INPUT)
    private String id;
}
