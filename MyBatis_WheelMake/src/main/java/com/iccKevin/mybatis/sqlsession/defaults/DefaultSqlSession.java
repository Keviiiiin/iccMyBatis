package com.iccKevin.mybatis.sqlsession.defaults;

import com.iccKevin.mybatis.cfg.Configuration;
import com.iccKevin.mybatis.sqlsession.SqlSession;
import com.iccKevin.mybatis.sqlsession.proxy.MapperProxy;
import com.iccKevin.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * SqlSession接口的实现类
 * 可以创建代理对象、释放资源
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * 代理dao接口，因为代理的是接口，所以newProxyInstance的第二个参数直接把dao接口传进去
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
    }

    /**
     * 用于释放资源
     */
    @Override
    public void close() {
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
