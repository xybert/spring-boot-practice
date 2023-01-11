package com.xybert.springbootmybatis.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常枚举类
 * @date 2022/10/19 16:06
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInterface {

    USER_NOT_EXIST("10101", "用户不存在"),
    USER_ALREADY_EXIST("10102", "用户【%s】已存在"),
    USER_INSERT_FAIL("10103", "用户新增失败"),
    USER_DELETE_FAIL("10104", "用户删除失败"),
    USER_UPDATE_FAIL("10105", "用户更新失败"),
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
