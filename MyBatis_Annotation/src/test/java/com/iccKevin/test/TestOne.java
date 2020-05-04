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

public class TestOne {

    /**
     * 测试查询账户信息时附带有用户信息
     * @param args
     */
    public static void main(String[] args)throws Exception {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession session = factory.openSession();
        IAccountDao accountDao = session.getMapper(IAccountDao.class);

        List<Account> accounts = accountDao.findAll();
        for(Account account : accounts){
            System.out.println(account);
            System.out.println(account.getUser());
        }

        session.close();
        in.close();
    }
}
