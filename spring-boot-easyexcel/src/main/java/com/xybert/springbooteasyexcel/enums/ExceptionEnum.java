package com.xybert.springbooteasyexcel.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 状态码规约
 * @date 2022/11/24 13:42
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInterface {

    EXCEL_PARSE_ERROR("10601", "Excel文件解析失败"),
    NO_DATA_EXIST("10602", "数据不存在，无法导出"),
    DATA_EXPORT_FAIL("10603", "数据导出失败"),
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
