package com.rainbow.practice.threadlocal.userinfo.annotation;

import com.rainbow.practice.threadlocal.userinfo.enums.InjectType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description 解决用户id转换问题
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectUser {

    InjectType type() default InjectType.INTERCEPTOR;

}
