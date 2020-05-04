package com.iccKevin.dao;

import com.iccKevin.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有账户信息
     * 实现多表查询后，可以获得账户对应的用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 根据uid查询账户信息
     * @return
     */
    List<Account> findByUid(Integer uid);
}
