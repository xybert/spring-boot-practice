package com.xybert.springbootmybatis.service.impl;

import cn.hutool.core.date.DateTime;
import com.google.common.collect.Lists;
import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.enums.UserOperateEnum;
import com.xybert.springbootmybatis.exception.CustomException;
import com.xybert.springbootmybatis.mapper.UserMapper;
import com.xybert.springbootmybatis.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:40
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 查询所有用户信息
     *
     * @return List<User> 用户列表
     */
    @Override
    public List<User> listUsers() {
        List<User> users = userMapper.selectAllUser();
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }
        return users;
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    @Override
    public User selectUserById(Long id) {
        return Optional.ofNullable(userMapper.selectUserById(id))
                .orElseThrow(() -> new CustomException(UserOperateEnum.USER_NOT_EXIST));
    }

    /**
     * 根绝用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    @Override
    public User selectUserByName(String name) {
        return Optional.ofNullable(userMapper.selectUserByName(name))
                .orElseThrow(() -> new CustomException(UserOperateEnum.USER_NAME_NOT_EXIST, name));
    }

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User 用户
     */
    @Override
    public User insertUser(User userInfo) {
        User user = userMapper.selectUserByName(userInfo.getName());
        if (user != null) {
            throw new CustomException(UserOperateEnum.USER_ALREADY_EXIST, userInfo.getName());
        }
        Date date = new Date();
        userInfo.setCreateTime(date);
        userInfo.setUpdateTime(date);
        if (userMapper.insertUser(userInfo) == 0) {
            throw new CustomException(UserOperateEnum.USER_INSERT_FAIL);
        }
        return userMapper.selectUserByName(userInfo.getName());
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true-成功 false-失败
     */
    @Override
    public User deleteUserById(Long id) {
        User user = userMapper.selectUserById(id);
        if (userMapper.deleteUserById(id) == 0) {
            throw new CustomException(UserOperateEnum.USER_DELETE_FAIL);
        }
        return user;
    }

    /**
     * 修改用户信息
     * 成功则返回修改后的信息，失败返回修改前的信息
     *
     * @param userInfo 用户
     * @param id 用户id
     * @return User 用户
     */
    @Override
    public User updateUser(User userInfo, Long id) {
        userMapper.selectUserById(id);
        userInfo.setId(id);
        userInfo.setUpdateTime(new Date());
        if (userMapper.updateUser(userInfo) == 0) {
            throw new CustomException(UserOperateEnum.USER_UPDATE_FAIL);
        }
        return userMapper.selectUserById(id);
    }
}
