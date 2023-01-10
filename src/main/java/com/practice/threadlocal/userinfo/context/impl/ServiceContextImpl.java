package com.practice.threadlocal.userinfo.context.impl;

import com.practice.threadlocal.userinfo.context.ServiceContext;
import com.practice.threadlocal.userinfo.context.UserInfo;
import com.practice.threadlocal.userinfo.context.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
//@Component
public class ServiceContextImpl implements ServiceContext {

    private static final ThreadLocal<String> USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<>();

//    public ServiceContextImpl(UserInfoService userInfoService) {
//        this.userInfoService = userInfoService;
//    }

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public String currentUserid() {
        String userid = USER_ID.get();
        if (userid == null) {
            UserInfo userInfo = setUserInfo();
            userid = userInfo.getId();
        }

        return userid;
    }

    @Override
    public UserInfo currentUserInfo() {
        UserInfo userInfo = USER_INFO.get();
        if (userInfo == null) {
            userInfo = setUserInfo();
        }

        return  userInfo;
    }

    @Override
    public void clear() {
        USER_ID.remove();
        USER_INFO.remove();
    }

    @Override
    public void extract() {
        setUserInfo();
    }

    private UserInfo setUserInfo() {

        UserInfo userInfo = userInfoService.getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfo();
        }

        USER_INFO.set(userInfo);
        USER_ID.set(userInfo.getId());

        return userInfo;
    }
}
