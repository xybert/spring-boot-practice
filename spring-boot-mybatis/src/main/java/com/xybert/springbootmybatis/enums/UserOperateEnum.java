package com.xybert.springbootmybatis.enums;

import com.xybert.springbootmybatis.exception.ErrorCodeBase;
import lombok.Getter;

/**
 * @author xybert
 * @description 用户操作失败状态码
 * @date 2022/10/19 16:06
 */

@Getter
public enum UserOperateEnum implements ErrorCodeBase {
    /**
     * 用户操作状态码
     */
    USER_NOT_EXIST("10400", "用户不存在", "user doesn't exist."),
    USER_NAME_NOT_EXIST("10401", "用户【%s】不存在", "user %s doesn't exist."),
    USER_ALREADY_EXIST("10500", "用户【%s】已存在", "user %s already exist"),
    USER_INSERT_FAIL("10600", "用户添加失败", "failed to insert user"),
    USER_DELETE_FAIL("10700", "用户删除失败", "failed to delete user"),
    USER_UPDATE_FAIL("10800", "用户更新失败", "failed to update user"),
    ;

    final String code;
    final String cnMsg;
    final String enMsg;

    UserOperateEnum(String code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }
}
