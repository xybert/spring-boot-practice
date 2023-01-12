package com.xybert.springbootemail.controller;

import com.xybert.springbootemail.enums.ExceptionEnum;
import com.xybert.springbootemail.util.MailUtils;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.BaseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xybert
 * @description
 * @date 2023/01/12 11:20
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailUtils mailUtils;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @PostMapping("/simple")
    public BaseResult sendSimpleMail(@RequestParam("email") String email) {
        String subject = "邮件发送测试";
        String content = "发送一份简单邮件";
        mailUtils.sendSimpleMail(email, subject, content);
        return BaseResult.success();
    }

    @PostMapping("/template")
    public BaseResult sendTemplateMail(@RequestParam("email") String email) {
        String subject = "邮件发送测试";
        Map<String, Object> templateValue = new HashMap<>();
        templateValue.put("toEmail", email);
        templateValue.put("fromEmail", fromEmail);
        String templatePath = "template_mail";
        try {
            mailUtils.sendTemplateMail(email, subject, templateValue, templatePath);
        } catch (Exception e) {
            throw new BaseException(ExceptionEnum.MAIL_SEND_FAIL);
        }
        return BaseResult.success();
    }
}
