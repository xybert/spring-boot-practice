package com.xybert.springbooteasyexcel.exception;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xybert
 * @description 异常基类
 * @date 2022/11/24 13:42
 */

public interface ExceptionBase {

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
     * 获取异常信息
     *
     * @return String
     */
    String getMessage();

    /**
     * 异常对象相互转换
     *
     * @param toValueType toValueType
     * @param args        format转换参数
     * @return Exception
     */
    default <T extends Exception> T convert(Class<T> toValueType, Object... args) {
        return convert(toValueType, null, args);
    }

    /**
     * 异常对象相互转换
     *
     * @param toValueType toValueType
     * @param e           e
     * @param args        format转换参数
     * @return Exception
     */
    default <T extends Exception> T convert(Class<T> toValueType,
                                            Throwable e,
                                            Object... args) {
        try {
            // 判断当前类是否实现ExceptionBase接口
            boolean assignable = ExceptionBase.class.isAssignableFrom(toValueType);

            if (assignable) {
                // BasicException(String code, String cnMsg, String enMsg, Throwable e, Object... args)
                // ErrorException(String code, String cnMsg, String enMsg, Throwable e, Object... args)
                Constructor<T> constructor =
                        toValueType.getConstructor(String.class, String.class, String.class, Throwable.class, Object[].class);
                // 如果CnMsg为空,则填充Message
                String cnMsg = StringUtils.isBlank(this.getCnMsg()) ? this.getMessage() : this.getCnMsg();
                return constructor.newInstance(this.getCode(), cnMsg, this.getEnMsg(), e, args);
            }

            throw new UnsupportedOperationException();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
            throw new UnsupportedOperationException(ex);
        }
    }
}
