package com.rainbow.practice.mybatisplus.convertDto;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
public class QueryParamUtil {

    public static class QueryParam {
        private String filed;
        private String expr;

        public String getFiled() {
            return filed;
        }

        public void setFiled(String filed) {
            this.filed = filed;
        }

        public String getExpr() {
            return expr;
        }

        public void setExpr(String expr) {
            this.expr = expr;
        }
    }

    public <T> QueryWrapper<T> convert(T data) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            QueryExpression annotation = field.getAnnotation(QueryExpression.class);
            QueryExpression.Type queryExpressionValue = annotation.value();
            Object value = field.get(data);
            switch (queryExpressionValue) {
                case EQ: {}
                defalut: {}
            }
            queryWrapper.eq(field.getName(), value);
        }
        return queryWrapper;
    }
}
