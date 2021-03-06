package com.iccKevin.test;

import com.iccKevin.dao.IUserDao;
import com.iccKevin.domain.User;
import com.iccKevin.mybatis.io.Resources;
import com.iccKevin.mybatis.sqlsession.SqlSession;
import com.iccKevin.mybatis.sqlsession.SqlSessionFactory;
import com.iccKevin.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件，获得加载了配置文件的输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.SqlSessionFactoryBuilder根据输入流构建工厂，返回SqlSessionFactory的实现类
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象，返回DAO接口的实现类，实现类对dao接口的方法进行了增强
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }
}
