package com.xybert.springbooteasyexcel.controller;

import com.xybert.springbooteasyexcel.common.BaseResult;
import com.xybert.springbooteasyexcel.constant.SystemResultCode;
import com.xybert.springbooteasyexcel.entity.User;
import com.xybert.springbooteasyexcel.exception.SystemException;
import com.xybert.springbooteasyexcel.listener.UserListener;
import com.xybert.springbooteasyexcel.service.UserService;
import com.xybert.springbooteasyexcel.util.EasyExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
            throw new SystemException(SystemResultCode.EXCEL_PARSE_ERROR);
        }
        return BaseResult.success();
    }
}
