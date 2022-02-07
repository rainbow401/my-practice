package com.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/12/11 3:03 下午
 */
@Data
public class Client implements Serializable {
    private Long id;
    private String code;
}
