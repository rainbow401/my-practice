package com.practice.threadlocal.userinfo.context;

import org.springframework.stereotype.Service;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
public interface ServiceContext extends ServiceContextInternal {

    /**
     * userid
     * @return userid
     */
    String currentUserid();

    /**
     * userinfo
     * @return userinfo
     */
    UserInfo currentUserInfo();
}
