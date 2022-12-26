package com.xybert.springbooteasyexcel.service.impl;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybert.springbooteasyexcel.constant.SystemResultCode;
import com.xybert.springbooteasyexcel.entity.User;
import com.xybert.springbooteasyexcel.exception.SystemException;
import com.xybert.springbooteasyexcel.mapper.UserMapper;
import com.xybert.springbooteasyexcel.service.UserService;
import com.xybert.springbooteasyexcel.util.EasyExcelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xybert
 * @description UserServiceImpl
 * @date 2022/12/23 14:31
 */

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void exportUser(HttpServletResponse response) {
        String fileName = "用户信息表";
        List<User> users = userMapper.selectList(null);
        if (CollectionUtils.isEmpty(users)) {
            throw new SystemException(SystemResultCode.NO_DATA_EXIST);
        }
        try {
            setResponse(response, fileName);
            EasyExcelUtils.write(response.getOutputStream(), User.class, users);
        } catch (IOException e) {
            throw new SystemException(SystemResultCode.DATA_EXPORT_FAIL);
        }
    }

    private void setResponse(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 设置URLEncoder.encode可以防止中文乱码
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ExcelTypeEnum.XLSX.getValue());
    }
}
