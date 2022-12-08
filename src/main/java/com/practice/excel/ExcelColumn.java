package com.practice.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/10/29 9:03 上午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelColumn {

    /**
     * 列头名称
     */
    String headerName() default "";

    /**
     * 列名 (A,B,C)
     */
    String column() default "";
}
