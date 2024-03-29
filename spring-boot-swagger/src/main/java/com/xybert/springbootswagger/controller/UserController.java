package com.xybert.springbootswagger.controller;

import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootswagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author xybert
 * @description User接口类
 * @date 2022/11/10 16:42
 */

@RestController
@Api(tags = "用户信息管理接口")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    @ApiOperation(value = "查询所有用户(DONE)", httpMethod = "GET")
    public BaseResult listUser() {
        return BaseResult.success(new User().selectAll());
    }

    @GetMapping("/get")
    @ApiOperation(value = "根据id查询用户(DONE)", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户id", dataTypeClass = Long.class, required = true)
    public BaseResult getUser(@RequestParam("id") Long id) {
        return BaseResult.success(Collections.singletonList(new User().selectById(id)));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加用户(DONE)", httpMethod = "POST")
    @Parameters(value = {
            @Parameter(name = "account", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true),
            @Parameter(name = "salt", description = "盐值", required = true),
            @Parameter(name = "sex", description = "性别"),
            @Parameter(name = "age", description = "年龄"),
            @Parameter(name = "tel", description = "手机号码"),
            @Parameter(name = "email", description = "邮箱")
    })
    public BaseResult saveUser(@RequestBody User user) {
        if (!user.insert()) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户(DONE)", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "用户id", dataTypeClass = Long.class, required = true)
    public BaseResult deleteUser(@RequestParam("id") Long id) {
        if (!new User().deleteById(id)) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新用户(DONE)", httpMethod = "PUT")
    @Parameters(value = {
            @Parameter(name = "id", description = "用户id", required = true),
            @Parameter(name = "account", description = "用户名"),
            @Parameter(name = "sex", description = "性别"),
            @Parameter(name = "age", description = "年龄"),
            @Parameter(name = "tel", description = "手机号码"),
            @Parameter(name = "email", description = "邮箱")
    })
    public BaseResult updateUser(@RequestBody User user) {
        if (!user.updateById()) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }
}
