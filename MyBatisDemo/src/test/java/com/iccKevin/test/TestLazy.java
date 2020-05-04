package com.iccKevin.test;

import com.iccKevin.dao.IAccountDao;
import com.iccKevin.dao.IUserDao;
import com.iccKevin.domain.Account;
import com.iccKevin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: 测试延迟加载
 * @author: iccKevin
 * @create: 2020-05-03 22:33
 **/
public class TestLazy {
    private IAccountDao accountDao;
    private IUserDao userDao;
    private SqlSession session;
    private InputStream is;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }

    /**
     * 测试根据uid查询账户信息
     */
    @Test
    public void testFindByUid(){
        List<Account> accounts = accountDao.findByUid(45);
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    /**
     * 测试一对多延迟加载
     * 查询用户信息时，需要获得账户信息再去查，不用时不查
     */
    @Test
    public void testOneToMulti(){
        List<User> users = userDao.findAll();
        for (User u: users) {
            System.out.println(u);
//            System.out.println(u.getAccounts());
        }
    }
}