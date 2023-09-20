package com.rainbow.practice.process.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yzh
 * @Date: 2023/9/20
 * @Description:
 */
@Getter
@Setter
public class ProcessDefine {

    private String key;

    private String name;

    private Base nodes;

    private String description;
}
