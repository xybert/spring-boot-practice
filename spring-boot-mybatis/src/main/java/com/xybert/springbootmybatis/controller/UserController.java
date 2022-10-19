package com.xybert.springbootmybatis.controller;

import com.sun.istack.internal.NotNull;
import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:40
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/info")
    public User getUserInfo(@NotNull Long id) {
        return userService.selectUserById(id);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody @Validated User user) {
        return userService.insertUser(user);
    }

    @DeleteMapping("/delete")
    public User deleteUser(@NotNull Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody @Validated User user, @NotNull Long id) {
        return userService.updateUser(user, id);
    }
}
