package com.xybert.springbootupload.enums;

import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xybert
 * @description 异常枚举类
 * @date 2023/01/11 14:52
 */

@Getter
@AllArgsConstructor
public enum ExceptionEnum implements BaseErrorInterface {

    EMPTY_FILE("10401", "文件为空"),
    PATH_NOT_EXIST("10402", "上传路径不存在"),
    UPLOAD_FAIL("10403", "文件上传失败"),
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
