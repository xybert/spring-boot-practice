package com.xybert.springbootexception.exception;

import com.alibaba.fastjson2.JSON;
import com.xybert.springbootexception.result.BaseErrorInterface;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author xybert
 * @description 异常基类
 * @date 2022/12/30 16:30
 */

@Data
public class BaseException extends RuntimeException implements BaseErrorInterface, Serializable {

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

    public BaseException(String message, Object... args) {
        super(BaseErrorInterface.formatMsg(message, args));
    }

    public BaseException(String message, Throwable cause, Object... args) {
        super(BaseErrorInterface.formatMsg(message, args), cause);
    }

    public BaseException(String code, String message, Object... args) {
        super(BaseErrorInterface.formatMsg(message, args));
        this.code = code;
        this.message = BaseErrorInterface.formatMsg(message, args);
    }

    public BaseException(String code, String message, Throwable cause, Object... args) {
        super(BaseErrorInterface.formatMsg(message, args), cause);
        this.code = code;
        this.message = BaseErrorInterface.formatMsg(message, args);
    }

    public BaseException(BaseErrorInterface baseErrorInterface, Object... args) {
        super(BaseErrorInterface.formatMsg(baseErrorInterface.getMessage(), args));
        this.code = baseErrorInterface.getCode();
        this.message = BaseErrorInterface.formatMsg(baseErrorInterface.getMessage(), args);
    }

    public BaseException(BaseErrorInterface baseErrorInterface, Throwable cause, Object... args) {
        super(BaseErrorInterface.formatMsg(baseErrorInterface.getMessage(), args), cause);
        this.code = baseErrorInterface.getCode();
        this.message = BaseErrorInterface.formatMsg(baseErrorInterface.getMessage(), args);
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
