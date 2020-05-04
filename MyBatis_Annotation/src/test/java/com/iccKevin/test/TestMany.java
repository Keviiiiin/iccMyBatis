package com.iccKevin.test;

import com.iccKevin.dao.IAccountDao;
import com.iccKevin.dao.IUserDao;
import com.iccKevin.domain.Account;
import com.iccKevin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @description: 测试一对多查询
 * @author: iccKevin
 * @create: 2020-05-04 19:14
 **/
public class TestMany {
    /**
     * 测试查询用户时附带有所有账户信息
     * @param args
     */
    public static void main(String[] args)throws Exception {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }

        session.close();
        in.close();
    }
}