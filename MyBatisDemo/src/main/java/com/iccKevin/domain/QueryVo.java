package com.iccKevin.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 存放查询条件的包装类
 * @author: iccKevin
 * @create: 2020-05-02 17:45
 **/
public class QueryVo implements Serializable {

    private User user;
    //加入一个 List 集合用于封装参数
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}