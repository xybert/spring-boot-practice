package com.xybert.springbootjpa.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常枚举类
 * @date 2022/12/31 16:54
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInterface {

    USER_NOT_EXIST("43101", "用户不存在"),
    USER_ALREADY_EXIST("43102", "用户已存在"),
    ACCOUNT_DUPLICATE("43103", "用户名重复"),
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
