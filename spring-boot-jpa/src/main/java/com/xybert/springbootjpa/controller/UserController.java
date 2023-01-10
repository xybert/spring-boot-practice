package com.xybert.springbootjpa.controller;

import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootjpa.entity.User;
import com.xybert.springbootjpa.entity.dto.UserDto;
import com.xybert.springbootjpa.entity.request.UserRequest;
import com.xybert.springbootjpa.service.UserService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BaseResult listAll(@RequestBody UserRequest userRequest) {
        Pair<Long, List<User>> users = userService.listAll(userRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("projects", users.getRight());
        result.put("pageSize", userRequest.getPageSize());
        result.put("totalCount", users.getLeft());
        return BaseResult.success(result);
    }

    @GetMapping("/get")
    public BaseResult getOne(@RequestParam("id") Long id) {
        return BaseResult.success(userService.getOne(id));
    }

    @PostMapping("/save")
    public BaseResult addOne(@RequestBody UserDto userDto) {
        return userService.addOne(userDto);
    }

    @DeleteMapping("/delete")
    public BaseResult deleteOne(@RequestParam("id") Long id) {
        return userService.deleteOne(id);
    }

    @DeleteMapping("/deleteBatch")
    public BaseResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        return userService.deleteBatch(ids);
    }

    @PutMapping("/update")
    public BaseResult updateOne(@RequestBody UserDto userDto) {
        return userService.updateOne(userDto);
    }
}
