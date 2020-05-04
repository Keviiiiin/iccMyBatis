package com.iccKevin.test;

import com.iccKevin.dao.IAnotherUserDao;
import com.iccKevin.domain.AnotherUser;
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
 * @description: 测试列名和属性名不对应的情况
 * @author: iccKevin
 * @create: 2020-05-04 17:51
 **/
public class TestAnoCRUD {
    private InputStream is;
    private SqlSession session;
    private IAnotherUserDao userDao;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        userDao = session.getMapper(IAnotherUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }
    @Test
    public void testFindAll(){
        List<AnotherUser> users = userDao.findAll();
        for (AnotherUser user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testInsert(){
        AnotherUser user = new AnotherUser();
        user.setUserName("annotation");
        user.setUserSex("男");
        userDao.insertUser(user);
    }
    @Test
    public void testUpdate(){
        AnotherUser user = new AnotherUser();
        user.setUserId(55);
        user.setUserName("annotation");
        user.setUserSex("男");
        user.setUserAddress("北京");
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(55);
    }
}