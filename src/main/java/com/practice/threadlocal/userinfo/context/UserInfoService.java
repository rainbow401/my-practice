package com.practice.threadlocal.userinfo.context;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
public interface UserInfoService {

    /**
     * getUserid
     * @return getUserid
     */
    String getUserid();

    /**
     * getUserInfo
     * @return getUserInfo
     */
    UserInfo getUserInfo();
}
