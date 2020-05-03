package com.iccKevin.dao;

import com.iccKevin.domain.QueryVo;
import com.iccKevin.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 新增用户
     * @param user
     * @return 影响数据库记录的行数
     */
    int insertUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(int id);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据传入的条件查询
     * @param user
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 查询id集合中的用户
     * @param vo
     * @return
     */
    List<User> findInIds(QueryVo vo);
}
