package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Description:
 * @Author: yanzhihao
 * @Date: 2022/1/27 11:12 上午
 */
@Data
public class TicketTel {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String tel;

    private Integer status;
}
