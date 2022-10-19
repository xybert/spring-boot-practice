package com.xybert.springbootmybatis;

import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SelectUserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        List<User> users = userMapper.selectAllUser();
        System.out.println(users);
        User user = userMapper.selectUserById(1L);
        System.out.println(user);
        User user1 = userMapper.selectUserByName("xybert");
        System.out.println(user1);
    }
}
