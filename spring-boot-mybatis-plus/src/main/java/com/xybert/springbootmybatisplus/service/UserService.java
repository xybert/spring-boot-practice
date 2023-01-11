package com.xybert.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootmybatisplus.entity.User;

import java.util.List;

/**
 * @author xybert
 * @description UserService
 * @date 2022/10/24 18:20
 */

public interface UserService extends IService<User> {

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    List<User> listUser();

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    User getUserById(Long id);

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User
     */
    BaseResult insertUser(User userInfo);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 1-成功 0-失败
     */
    BaseResult deleteUserById(Long id);

    /**
     * 根据id批量删除用户
     *
     * @param ids 用户id列表
     * @return 1-成功 0-失败
     */
    BaseResult deleteUserByIds(List<Long> ids);

    /**
     * 更新用户信息
     *
     * @param id 用户id
     * @param userInfo 用户信息
     * @return 1-成功 0-失败
     */
    BaseResult updateUserById(User userInfo, Long id);

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size 每页显示记录数
     * @return Page
     */
    Page<User> selectPage(int current, int size);
}
