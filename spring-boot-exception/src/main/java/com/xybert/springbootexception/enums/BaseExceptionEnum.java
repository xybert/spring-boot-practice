package com.xybert.springbootexception.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常状态码规约
 * @date 2022/12/30 11:10
 */

@Getter
@AllArgsConstructor
public enum BaseExceptionEnum implements BaseErrorInterface {

    SYSTEM_ERROR("00099", "系统内部异常"),
    NETWORK_ERROR("00098", "网络异常"),
    PARAM_ERROR("00097", "参数错误"),
    NO_PERMISSION("00096", "权限不足"),
    NO_AUTH("00095", "未授权"),
    NOT_FOUND("00094", "未找到该数据"),
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
