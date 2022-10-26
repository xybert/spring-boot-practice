package com.xybert.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybert.springbootmybatisplus.entity.User;
import com.xybert.springbootmybatisplus.mapper.UserMapper;
import com.xybert.springbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xybert
 * @description UserServiceImpl
 * @date 2022/10/24 18:22
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
