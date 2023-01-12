package com.xybert.springbootemail.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常枚举类
 * @date 2023/01/12 14:06
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInterface {

    MAIL_SEND_FAIL("10701", "邮件发送失败"),
    ;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;
}
