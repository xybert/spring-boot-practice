package com.xybert.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xybert.springbootmybatisplus.common.BaseResult;
import com.xybert.springbootmybatisplus.entity.User;
import com.xybert.springbootmybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author xybert
 * @description User 控制类
 * @date 2022/10/24 19:32
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public BaseResult<User> listUser() {
        return BaseResult.success(userService.listUser());
    }

    @GetMapping("/get")
    public BaseResult<User> getUser(@RequestParam("id") Long id) {
        return BaseResult.success(Collections.singletonList(userService.getUserById(id)));
    }

    @PostMapping("/save")
    public BaseResult<User> saveUser(@RequestBody User userInfo) {
        return userService.insertUser(userInfo);
    }

    @PostMapping("saveBatch")
    public BaseResult<User> saveBatchUser(@RequestBody List<User> userInfos) {
        if (!userService.saveBatch(userInfos)) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    public BaseResult<User> deleteUser(@RequestParam("id") Long id) {
        return userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteBatch")
    public BaseResult<User> deleteBatchUser(@RequestParam("ids") List<Long> ids) {
        return userService.deleteUserByIds(ids);
    }

    @PutMapping("/update")
    public BaseResult<User> updateUser(@RequestBody User userInfo, @RequestParam("id") Long id) {
        return userService.updateUserById(userInfo, id);
    }

    @GetMapping("/page")
    public BaseResult<IPage> selectPage(@RequestParam("current") int current, @RequestParam("size") int size) {
        IPage<User> page = userService.selectPage(current, size);
        // Page<User> userPage = new Page<>(current, size);
        // QueryWrapper queryWrapper = new QueryWrapper<>();
        // IPage<User> page = userService.page(userPage, queryWrapper);
        return BaseResult.success(Collections.singletonList(page));
    }
}
