package com.iccKevin.mybatis.sqlsession.defaults;

import com.iccKevin.mybatis.cfg.Configuration;
import com.iccKevin.mybatis.sqlsession.SqlSession;
import com.iccKevin.mybatis.sqlsession.SqlSessionFactory;


/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * SqlSessionFactory接口的实现类，用于生产SqlSession对象
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     * @return 返回SqlSession接口的实现类
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
