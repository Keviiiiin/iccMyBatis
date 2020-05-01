package com.iccKevin.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * 自定义mybatis的配置类
 * 里面存放的是主配置文件中连接数据库的信息，以及映射配置文件中的信息（封装在Mapper对象中）
 * Mapper对象存放的是sql语句和dao实体类的全限定类名，因为有多个表，所以有多个Mapper对象，用Map来存储，键为com.iccKevin.dao.IUserDao.findAll
 */
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String,Mapper> mappers = new HashMap<String,Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        this.mappers.putAll(mappers);//此处需要使用追加的方式，把输入的Map集合追加到当前Map中，因为XMLConfigBuilder中要循环调用
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
