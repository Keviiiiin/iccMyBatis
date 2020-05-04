package com.iccKevin.dao;

import com.iccKevin.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 *
 * 用户的持久层接口
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "address",property = "address"),
//            注意，下面的column写id,因为要用用户的id作为参数传入findByUid方法
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.iccKevin.dao.IAccountDao.findByUid",fetchType = FetchType.LAZY))

    })
    List<User> findAll();

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    void insertUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);

    /**
     * 根据id查询
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findById(int id );
}
