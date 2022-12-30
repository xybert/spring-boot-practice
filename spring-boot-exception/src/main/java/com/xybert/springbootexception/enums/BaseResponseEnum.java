package com.xybert.springbootexception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 响应结果基础枚举
 * @date 2022/12/30 15:20
 */

@Getter
@AllArgsConstructor
public enum BaseResponseEnum {

    SUCCESS("00000", "操作成功"),
    FAIL("09991", "操作失败"),
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
