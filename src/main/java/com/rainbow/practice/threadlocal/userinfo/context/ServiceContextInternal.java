package com.rainbow.practice.threadlocal.userinfo.context;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
public interface ServiceContextInternal {

    /**
     * 清除
     */
    void clear();

    /**
     * 存放信息
     */
    void extract();
}
