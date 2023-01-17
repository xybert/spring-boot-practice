package com.xybert.springbootmybatis.service.impl;

import com.google.common.collect.Lists;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.enums.ExceptionEnum;
import com.xybert.springbootmybatis.mapper.UserMapper;
import com.xybert.springbootmybatis.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author xybert
 * @description 用户增删改查
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
                .orElseThrow(() -> new BaseException(ExceptionEnum.USER_NOT_EXIST));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param account 用户名
     * @return User 用户
     */
    @Override
    public User selectUserByName(String account) {
        return Optional.ofNullable(userMapper.selectUserByAccount(account))
                .orElseThrow(() -> new BaseException(ExceptionEnum.USER_NOT_EXIST));
    }

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User 用户
     */
    @Override
    public User insertUser(User userInfo) {
        User user = userMapper.selectUserByAccount(userInfo.getAccount());
        if (user != null) {
            throw new BaseException(ExceptionEnum.USER_ALREADY_EXIST, userInfo.getAccount());
        }
        if (userMapper.insertUser(userInfo) == 0) {
            throw new BaseException(ExceptionEnum.USER_INSERT_FAIL);
        }
        return selectUserByName(userInfo.getAccount());
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true-成功 false-失败
     */
    @Override
    public User deleteUserById(Long id) {
        User user = selectUserById(id);
        if (userMapper.deleteUserById(id) == 0) {
            throw new BaseException(ExceptionEnum.USER_DELETE_FAIL);
        }
        return user;
    }

    /**
     * 修改用户信息
     * 成功则返回修改后的信息，失败返回修改前的信息
     *
     * @param userInfo 用户
     * @param id       用户id
     * @return User 用户
     */
    @Override
    public User updateUser(User userInfo, Long id) {
        selectUserById(id);
        if (userMapper.updateUser(userInfo) == 0) {
            throw new BaseException(ExceptionEnum.USER_UPDATE_FAIL);
        }
        return selectUserById(id);
    }
}
