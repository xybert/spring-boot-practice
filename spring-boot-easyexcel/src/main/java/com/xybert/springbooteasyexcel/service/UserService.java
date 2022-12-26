package com.xybert.springbooteasyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xybert.springbooteasyexcel.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xybert
 * @description UserService
 * @date 2022/12/23 14:30
 */

public interface UserService extends IService<User> {

    /**
     * 导出用户信息
     *
     * @param response HttpServletResponse
     */
    void exportUser(HttpServletResponse response);
}
