package com.rainbow.practice.auth.vo;

import lombok.Data;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/12/11 3:49 下午
 */
@Data
public class AccessTokenVO {
    private String assessToken;
    private Long expireTime;
}
