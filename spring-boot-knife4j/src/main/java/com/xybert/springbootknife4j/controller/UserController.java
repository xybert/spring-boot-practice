package com.xybert.springbootknife4j.controller;

import com.xybert.springbootknife4j.common.BaseResult;
import com.xybert.springbootknife4j.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author xybert
 * @description User接口类
 * @date 2022/11/11 10:37
 */

@RestController
@Tag(name = "用户信息管理接口")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    @Operation(summary = "查询所有用户", method = "GET", tags = {"DONE"})
    public BaseResult<User> listUser() {
        return BaseResult.success(new User().selectAll());
    }

    @GetMapping("/get")
    @Operation(summary = "根据id查询用户", method = "GET", tags = {"DONE"})
    @Parameter(name = "id", description = "用户id", example = "1", required = true)
    public BaseResult<User> getUser(@RequestParam("id") Long id) {
        return BaseResult.success(Collections.singletonList(new User().selectById(id)));
    }

    @PostMapping("/save")
    @Operation(summary = "添加用户", method = "POST", tags = {"DONE"})
    public BaseResult saveUser(@RequestBody User user) {
        if (!user.insert()) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户", method = "DELETE", tags = {"DONE"})
    @Parameter(name = "id", description = "用户id", example = "1", required = true)
    public BaseResult deleteUser(@RequestParam("id") Long id) {
        if (!new User().deleteById(id)) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户(DONE)", method = "PUT", tags = {"DONE"})
    @Parameters(
            @Parameter
    )
    public BaseResult updateUser(@RequestBody User user) {
        if (!user.updateById()) {
            return BaseResult.fail();
        }
        return BaseResult.success();
    }
}
