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
import java.util.Date;
import java.util.List;


/**
 * @description: 测试基于注解的CRUD
 * @author: iccKevin
 * @create: 2020-05-04 17:07
 **/
public class TestCRUD {
    private InputStream is;
    private SqlSession session;
    private IUserDao userDao;
    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        is.close();
    }
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("annotation");
        user.setSex("男");
        userDao.insertUser(user);
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(55);
        user.setUsername("annotation");
        user.setSex("男");
        user.setAddress("北京");
        userDao.updateUser(user);
    }
    @Test
    public void testDelete(){
        userDao.deleteUser(57);
    }
}