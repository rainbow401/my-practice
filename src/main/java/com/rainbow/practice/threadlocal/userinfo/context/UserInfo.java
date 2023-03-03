package com.rainbow.practice.threadlocal.userinfo.context;

/**
 * @author yanzhihao
 * @date 2023/1/10
 * @Description
 */
public class UserInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                '}';
    }
}
