package com.rainbow.practice.enums;

/**
 * @Author: YanZhiHao
 * @Date: 2023/3/14
 * @Description:
 */
public interface EnumCode<T> {

    /**
     * 获取枚举转换key
     */
    T getCode();
}
