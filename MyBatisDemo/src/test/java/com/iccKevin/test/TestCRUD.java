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
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @description: 测试增删改查功能
 * @author: iccKevin
 * @create: 2020-05-02 09:19
 **/
public class TestCRUD {

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

    @Test
    public void testInsert(){
        User user = new User();
//        user.setId(31);
        user.setUsername("张三");
        user.setAddress("陕西西安");
        user.setBirthday(new Date());
        user.setSex("男");
        int rowCount = userDao.insertUser(user);
        System.out.println(user);
        System.out.println(rowCount);
    }

    @Test
    public void testDelete(){
        userDao.deleteUser(53);
    }

    @Test
    public void testUpdate(){
        User user = new User(51,"李四",new Date(),"男","北京");
        userDao.updateUser(user);
    }

    @Test
    public void testSelectOne(){
        User user = userDao.findById(51);
//        System.out.println(user);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        System.out.println(sdf.format(user.getBirthday()));
    }
}