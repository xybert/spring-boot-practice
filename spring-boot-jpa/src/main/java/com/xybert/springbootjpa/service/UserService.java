package com.xybert.springbootjpa.service;

import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;

import java.util.List;

/**
 * @author xybert
 * @description UserService
 * @date 2022/12/29 11:03
 */

public interface UserService {

    /**
     * 查询所有用户
     *
     * @param userRequest 请求参数
     * @return List<User>
     */
    List<User> listAll(UserRequest userRequest);

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    User getOne(Long id);

    /**
     * 添加用户
     *
     * @param userDto user信息
     */
    void addOne(UserDto userDto);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void deleteOne(Long id);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     */
    void updateOne(UserDto userDto);
}
