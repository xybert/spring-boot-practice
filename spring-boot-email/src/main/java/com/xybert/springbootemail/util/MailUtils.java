package com.xybert.springbootemail.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author xybert
 * @description 邮件工具类
 * @date 2023/01/12 10:48
 */

@Component
@Data
@Slf4j
public class MailUtils {
    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * html格式邮件
     *
     * @param to      收件人,多个参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(from);
        //邮件接收人,设置多个收件人地址
        InternetAddress[] internetAddressTo = InternetAddress.parse(to);
        messageHelper.setTo(internetAddressTo);
        message.setSubject(subject);
        messageHelper.setText(content, true);
        javaMailSender.send(message);
    }

    /**
     * 带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        javaMailSender.send(message);
    }

    /**
     * 带模板的邮件
     *
     * @param to      收件人
     * @param subject 主题
     */
    public void sendTemplateMail(String to, String subject, Map<String, Object> templateValue, String templatePath) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        Context context = new Context();
        context.setVariables(templateValue);
        String text = templateEngine.process(templatePath, context);
        messageHelper.setText(text, true);
        javaMailSender.send(message);
    }
}
