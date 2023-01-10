package com.xybert.springbootexception.result;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xybert
 * @description 基础接口类
 * @date 2022/12/31 22:46
 */

public interface BaseErrorInterface {

    /**
     * 获取响应码
     *
     * @return code
     */
    String getCode();

    /**
     * 获取响应消息
     *
     * @return message
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
    default <T extends Exception> T convert(Class<T> toValueType, Throwable e, Object... args) {
        try {
            boolean assignable = BaseErrorInterface.class.isAssignableFrom(toValueType);
            if (assignable) {
                Constructor<T> constructor = toValueType.getConstructor(String.class, String.class, Throwable.class, Object[].class);
                String msg = StringUtils.isBlank(this.getMessage()) ? e.getMessage() : this.getMessage();
                return constructor.newInstance(this.getCode(), msg, e, args);
            }
            throw new UnsupportedOperationException();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
            throw new UnsupportedOperationException(ex);
        }
    }

    static String formatMsg(String format, Object... args) {
        if (StringUtils.isBlank(format)) {
            return StringUtils.EMPTY;
        }
        if (ArrayUtils.isEmpty(args)) {
            return format;
        }
        try {
            return String.format(format, args);
        } catch (Exception e) {
            e.printStackTrace();
            return format;
        }
    }
}
