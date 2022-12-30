package com.xybert.springbootexception.result;

import com.alibaba.fastjson2.JSON;
import com.xybert.springbootexception.enums.BaseResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author xybert
 * @description 接口数据结构
 * @date 2022/12/30 10:45
 */

@Data
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 6675812521987115367L;

    /**
     * 响应码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Enum e) {
        Method[] methods = e.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (Objects.equals(method.getName(), "getCode")) {
                    this.code = method.invoke(e).toString();
                    continue;
                }
                if (Objects.equals(method.getName(), "getMessage")) {
                    this.message = method.invoke(e).toString();
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
                return;
            }
        }
    }

    public ApiResponse(Enum e, Object data) {
        Method[] methods = e.getClass().getDeclaredMethods();
        boolean errorHappened = false;
        for (Method method : methods) {
            try {
                if (Objects.equals(method.getName(), "getCode")) {
                    this.code = method.invoke(e).toString();
                    continue;
                }
                if (Objects.equals(method.getName(), "getMessage")) {
                    this.message = method.invoke(e).toString();
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
                errorHappened = true;
                break;
            }
        }
        if (!errorHappened) {
            this.data = data;
        }
    }

    public static ApiResponse success() {
        return new ApiResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS.getMessage());
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(BaseResponseEnum.SUCCESS.getCode(), message);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS.getMessage(), data);
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(BaseResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static ApiResponse fail() {
        return new ApiResponse(BaseResponseEnum.SUCCESS.getCode(), BaseResponseEnum.SUCCESS.getMessage());
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(BaseResponseEnum.FAIL.getCode(), message);
    }

    public static ApiResponse fail(Exception exception) {
        return new ApiResponse(BaseResponseEnum.FAIL.getCode(), exception.getMessage());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
