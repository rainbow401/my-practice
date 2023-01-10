package com.practice;

import com.practice.threadlocal.userinfo.context.UserInfo;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
public class Test {

    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    public void test() {
        UserInfo userInfo = createUserInfo();
        UserInfo userInfo1 = createUserInfo();
    }

}
