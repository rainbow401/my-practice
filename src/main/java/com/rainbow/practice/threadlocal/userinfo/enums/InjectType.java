package com.rainbow.practice.threadlocal.userinfo.enums;

/**
 * @author yanzhihao
 * @date 2023/1/11
 * @Description
 */
public enum InjectType {

    /**
     * spring AOP
     */
    AOP("aop"),

    /**
     * mvc拦截器
     */
    INTERCEPTOR("interceptor");

    private String type;

    InjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
