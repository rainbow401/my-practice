package com.rainbow.practice.freemarker.domain.service.result.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private String id;

    private String username;
    /**
     *
     */
    @TableField(value = "CreatedById")
    private String createdById;

    /**
     *
     */
    @TableField(value = "CreatedAt")
    private Date createdAt;

    /**
     *
     */
    @TableField(value = "UpdatedById")
    private String updatedById;

    /**
     *
     */
    @TableField(value = "UpdatedAt")
    private Date updatedAt;

    /**
     *
     */
    @TableField(value = "DeletedById")
    private String deletedById;

    /**
     *
     */
    @TableField(value = "DeletedAt")
    private Date deletedAt;

    /**
     * 是否删除(0 否；1 是)
     */
    @TableField(value = "Deleted")
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
