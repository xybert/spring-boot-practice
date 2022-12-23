package com.xybert.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybert.springbootmybatisplus.common.BaseResult;
import com.xybert.springbootmybatisplus.constant.SystemResultCode;
import com.xybert.springbootmybatisplus.entity.User;
import com.xybert.springbootmybatisplus.exception.SystemException;
import com.xybert.springbootmybatisplus.mapper.UserMapper;
import com.xybert.springbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author xybert
 * @description UserServiceImpl
 * @date 2022/10/24 18:22
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    @Override
    public List<User> listUser() {
        // ActiveRecord模式
        // List<User> users = new User().selectAll();
        QueryWrapper wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return User
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = Optional.ofNullable(userMapper.selectById(id));
        // ActiveRecord 模式
        // User user = new User().selectById(id);
        return user.orElseThrow(() -> new SystemException(SystemResultCode.USER_NOT_EXIST));
    }

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User
     */
    @Override
    public BaseResult insertUser(User userInfo) {
        // ActiveRecord模式
        // boolean insert = userInfo.insert();
        int insert = userMapper.insert(userInfo);
        if (insert == 0) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 1-成功 0-失败
     */
    @Override
    public BaseResult deleteUserById(Long id) {
        // ActiveRecord模式
        // boolean insert = new User().deleteById(id);
        int delete = userMapper.deleteById(id);
        if (delete == 0) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    /**
     * 根据id批量删除用户
     *
     * @param ids 用户id列表
     * @return 1-成功 0-失败
     */
    @Override
    public BaseResult deleteUserByIds(List<Long> ids) {
        int deleteBatch = userMapper.deleteBatchIds(ids);
        if (deleteBatch == 0) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return 1-成功 0-失败
     */
    @Override
    public BaseResult updateUserById(User userInfo, Long id) {
        userInfo.setId(id);
        // QueryWrapper queryWrapper = new QueryWrapper<>();
        // int update = userMapper.update(userInfo, queryWrapper);
         int updateById = userMapper.updateById(userInfo);
        // ActiveRecord模式
        // boolean update = userInfo.update(queryWrapper);
        if (updateById == 0) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @Override
    public Page<User> selectPage(int current, int size) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        return userMapper.selectPage(page, queryWrapper);
    }
}
