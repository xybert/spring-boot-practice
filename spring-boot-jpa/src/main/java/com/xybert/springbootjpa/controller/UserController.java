package com.xybert.springbootjpa.controller;

import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;
import com.xybert.springbootjpa.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xybert
 * @description UserController
 * @date 2022/12/29 11:07
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> listAll(@RequestBody UserRequest userRequest) {
        return userService.listAll(userRequest);
    }

    @GetMapping("/get")
    public User getOne(@RequestParam("id") Long id) {
        return userService.getOne(id);
    }

    @PostMapping("/save")
    public void addOne(@RequestBody UserDto userDto) {
        userService.addOne(userDto);
    }

    @DeleteMapping("/delete")
    public void deleteOne(@RequestParam("id") Long id) {
        userService.deleteOne(id);
    }

    @PutMapping("/update")
    public void updateOne(@RequestBody UserDto userDto) {
        userService.updateOne(userDto);
    }
}
