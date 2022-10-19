package com.xybert.springbootmybatis.service;

import com.xybert.springbootmybatis.entity.User;

import java.util.List;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:39
 */

public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return List<User> 用户列表
     */
    List<User> listUsers();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    User selectUserById(int id);

    /**
     * 根绝用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    User selectUserByName(String name);

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User 用户
     */
    User insertUser(User userInfo);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true-成功 false-失败
     */
    User deleteUserById(int id);

    /**
     * 修改用户信息
     * 成功则返回修改后的信息，失败返回修改前的信息
     *
     * @param user 用户
     * @param id 用户id
     * @return User 用户
     */
    User updateUser(User user, int id);
}
