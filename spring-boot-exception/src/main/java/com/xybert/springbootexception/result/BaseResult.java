package com.xybert.springbootexception.result;

import com.alibaba.fastjson2.JSON;
import com.xybert.springbootexception.enums.BaseResultEnum;
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
public class BaseResult implements Serializable {

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

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(BaseErrorInterface baseErrorInterface) {
        this.code = baseErrorInterface.getCode();
        this.message = baseErrorInterface.getMessage();
    }

    public BaseResult(BaseErrorInterface baseErrorInterface, Object data) {
        this.code = baseErrorInterface.getCode();
        this.message = baseErrorInterface.getMessage();
        this.data = data;
    }

    public BaseResult(Enum e) {
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

    public BaseResult(Enum e, Object data) {
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

    public static BaseResult success() {
        return new BaseResult(BaseResultEnum.SUCCESS.getCode(), BaseResultEnum.SUCCESS.getMessage());
    }

    public static BaseResult success(String message) {
        return new BaseResult(BaseResultEnum.SUCCESS.getCode(), message);
    }

    public static BaseResult success(Object data) {
        return new BaseResult(BaseResultEnum.SUCCESS.getCode(), BaseResultEnum.SUCCESS.getMessage(), data);
    }

    public static BaseResult success(String message, Object data) {
        return new BaseResult(BaseResultEnum.SUCCESS.getCode(), message, data);
    }

    public static BaseResult fail() {
        return new BaseResult(BaseResultEnum.SUCCESS.getCode(), BaseResultEnum.SUCCESS.getMessage());
    }

    public static BaseResult fail(String message) {
        return new BaseResult(BaseResultEnum.FAIL.getCode(), message);
    }

    public static BaseResult fail(Exception exception) {
        return new BaseResult(BaseResultEnum.FAIL.getCode(), exception.getMessage());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
