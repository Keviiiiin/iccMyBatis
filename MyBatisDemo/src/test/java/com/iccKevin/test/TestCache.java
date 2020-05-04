package com.iccKevin.test;

import com.iccKevin.dao.IUserDao;
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

/**
 * @description: 测试缓存机制
 * @author: iccKevin
 * @create: 2020-05-04 10:16
 **/
public class TestCache {
    private IUserDao userDao;
    private SqlSession session;
    private InputStream is;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }
    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        User user1 = userDao.findById(43);
        System.out.println(user1);
        User user2 = userDao.findById(43);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    /**
     * 测试清空缓存的时机
     */
    @Test
    public void testClearCache(){
        User user1 = userDao.findById(43);
        System.out.println(user1);

        User user = new User();
        user.setId(43);
        user.setUsername("王二麻子");
        user.setSex("男");
        userDao.updateUser(user);

        User user2 = userDao.findById(43);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

}