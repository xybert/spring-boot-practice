package com.xybert.springbootmybatis.exception;

/**
 * @author xybert
 * @date 2022/10/19 14:43
 */

public interface ErrorCodeBase {

    /**
     * 获取错误码
     *
     * @return String
     */
    String getCode();

    /**
     * 获取中文错误信息
     *
     * @return String
     */
    String getCnMsg();

    /**
     * 获取英文错误信息
     *
     * @return String
     */
    String getEnMsg();

    /**
     * 格式化错误信息
     *
     * @param msg 错误信息
     * @param args args
     * @return String
     */
    default String format(String msg, Object... args) {
        return String.format(msg, args);
    }
}
