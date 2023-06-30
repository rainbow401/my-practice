package com.rainbow.practice.mybatisplus.convertDto;

import java.io.Serializable;

/**
 * @classname: SFunction
 * @create: 2019-09-25 12:57
 **/
@FunctionalInterface
public interface SFunction<T> extends Serializable {
    Object get(T source);
}