package com.iccKevin.dao;

import com.iccKevin.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户信息
     * @return
     */
    List<Account> findAll();
}
