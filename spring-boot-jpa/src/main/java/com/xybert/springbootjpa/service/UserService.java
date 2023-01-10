package com.xybert.springbootjpa.service;

import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;
import org.apache.commons.lang3.tuple.Pair;

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
     * @return Pair<Long, List<User>>
     */
    Pair<Long, List<User>> listAll(UserRequest userRequest);

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
    BaseResult addOne(UserDto userDto);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    BaseResult deleteOne(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 用户id列表
     */
    BaseResult deleteBatch(List<Long> ids);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     */
    BaseResult updateOne(UserDto userDto);
}
