package com.iccKevin.dao;

import com.iccKevin.domain.AnotherUser;
import com.iccKevin.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAnotherUserDao {
    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    @Results(id="anoUserMap",value = {
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "address",property = "userAddress"),
    })
    List<AnotherUser> findAll();

    /**
     * 插入用户
     * @param user
     */
    @ResultMap("anoUserMap")
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    void insertUser(AnotherUser user);

    /**
     * 更新用户
     * @param user
     */
    @ResultMap("anoUserMap")
    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUser(AnotherUser user);

    /**
     * 删除用户
     * @param id
     */
    @ResultMap("anoUserMap")
    @Delete("delete from user where id=#{id}")
    void deleteUser(int id);
}
