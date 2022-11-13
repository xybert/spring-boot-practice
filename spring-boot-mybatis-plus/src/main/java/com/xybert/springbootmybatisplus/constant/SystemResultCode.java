package com.xybert.springbootmybatisplus.constant;

import com.xybert.springbootmybatisplus.exception.ErrorCodeBase;
import lombok.Getter;

/**
 * @author xybert
 * @description 状态码规约
 * @date 2022/11/12 16:21
 */

@Getter
public enum SystemResultCode implements ErrorCodeBase {

    /**
     * 系统异常
     */
    SYSTEM_NOT_HANDLER_EXCEPTION("09999", "系统内部错误", "system error"),
    USER_NOT_EXIST("29001", "用户不存在", "user not exist"),
    ;

    /**
     * 状态码
     */
    final String code;
    /**
     * 中文提示信息
     */
    final String cnMsg;
    /**
     * 英文提示信息
     */
    final String enMsg;

    SystemResultCode(String code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }
}
