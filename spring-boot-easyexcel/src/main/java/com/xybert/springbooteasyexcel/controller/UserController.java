package com.xybert.springbooteasyexcel.controller;

import com.xybert.springbooteasyexcel.entity.User;
import com.xybert.springbooteasyexcel.enums.ExceptionEnum;
import com.xybert.springbooteasyexcel.listener.UserListener;
import com.xybert.springbooteasyexcel.service.UserService;
import com.xybert.springbooteasyexcel.util.EasyExcelUtils;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.BaseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xybert
 * @description User实体类
 * @date 2022/12/23 14:25
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/import")
    public BaseResult importUser(@RequestParam("file")MultipartFile file) {
        try {
            EasyExcelUtils.asyncReadModel(file.getInputStream(), User.class, new UserListener(userService));
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_PARSE_ERROR);
        }
        return BaseResult.success();
    }

    @GetMapping("/export")
    public void exportUser(HttpServletResponse response) {
        userService.exportUser(response);
    }
}
