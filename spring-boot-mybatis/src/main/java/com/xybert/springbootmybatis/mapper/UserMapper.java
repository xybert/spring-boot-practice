package com.xybert.springbootmybatis.mapper;

import com.xybert.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description user表访问层
 * @author xybert
 * @date 2022/10/18 14:50
 */

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return List<User> 用户列表
     */
    List<User> selectAllUser();

    /**
     * 根绝id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    User selectUserById(@Param("id") Long id);

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    User selectUserByName(@Param("name") String name);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 1-成功 0-失败
     */
    int insertUser(@Param("user") User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 1-成功 0-失败
     */
    int deleteUserById(@Param("id") Long id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     * @return 1-成功 0-失败
     */
    int updateUser(@Param("user") User user);
}
