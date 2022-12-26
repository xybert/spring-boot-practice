package com.xybert.springbooteasyexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybert.springbooteasyexcel.entity.User;
import com.xybert.springbooteasyexcel.mapper.UserMapper;
import com.xybert.springbooteasyexcel.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xybert
 * @description UserServiceImpl
 * @date 2022/12/23 14:31
 */

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {
}
