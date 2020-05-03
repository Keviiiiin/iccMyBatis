package com.iccKevin.test;

import com.iccKevin.dao.IAccountDao;
import com.iccKevin.dao.IUserDao;
import com.iccKevin.domain.Account;
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
    private SqlSession session;
    private InputStream is;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }
    @Test
    public void testOneToOne(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}