package com.rainbow.practice.mybatisplus.convertDto;

import java.lang.annotation.*;

/**
 * @author yanzhihao
 * @since 2023/6/30
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryExpression {

    Type value() default Type.EQ;

    enum Type {
        /**
         * 等于
         */
        EQ,
        /**
         * 不等于
         */
        NE,
        /**
         * 模糊
         */
        LIKE,
        /**
         * 大于
         */
        GT,
        /**
         * 大于等于
         */
        GE,
        /**
         * 小于
         */
        LT,
        /**
         * 小于等于
         */
        LE,
        NOT_NULL,
        IS_NULL,
        IN
    }
}
