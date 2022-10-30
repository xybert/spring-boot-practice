package com.xybert.springbootupload.exception;

/**
 * @author xybert
 * @description 全局异常基类
 * @date 2022/10/30 22:00
 */

public interface ErrorCodeBase {

    /**
     * 错误码
     *
     * @return String
     */
    String getCode();

    /**
     * 英文异常信息
     *
     * @return String
     */
    String getEnMsg();

    /**
     * 中文异常信息
     *
     * @return String
     */
    String getCnMsg();

    /**
     * 对字符串进行format处理
     *
     * @param msg  msg
     * @param args args
     * @return String
     */
    default String format(String msg, Object... args) {
        return String.format(msg, args);
    }
}
