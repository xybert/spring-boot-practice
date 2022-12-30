package com.xybert.springbootexception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常状态码规约
 * @date 2022/12/30 11:10
 */

@Getter
@AllArgsConstructor
public enum BaseExceptionEnum {

    SYSTEM_ERROR("09999", "系统内部异常"),
    NETWORK_ERROR("09998", "网络异常"),
    PARAM_ERROR("09997", "参数错误"),
    NO_PERMISSION("08991", "权限不足"),
    NO_AUTH("07991", "未授权"),
    NOT_FOUND("06991", "未找到该数据"),
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
