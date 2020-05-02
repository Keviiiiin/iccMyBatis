package com.iccKevin.domain;

/**
 * @description: 存放查询条件的包装类
 * @author: iccKevin
 * @create: 2020-05-02 17:45
 **/
public class QueryVo {
    //把User中的各种属性作为查询条件
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}