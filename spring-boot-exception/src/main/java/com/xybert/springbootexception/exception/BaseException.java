package com.xybert.springbootexception.exception;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xybert
 * @description 异常基类
 * @date 2022/12/30 16:30
 */

@Data
public class BaseException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5253831112698485809L;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseException)) {
            return false;
        }
        BaseException exception = (BaseException) obj;
        return Objects.equals(this.getCode(), exception.getCode()) &&
                Objects.equals(this.getMessage(), exception.getMessage());
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object errorCode = this.getCode();
        result = result * 59 + (errorCode == null ? 43 : errorCode.hashCode());
        Object errorMsg = this.getMessage();
        result = result * 59 + (errorMsg == null ? 43 : errorMsg.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
