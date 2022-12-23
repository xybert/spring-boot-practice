package com.xybert.springbootmybatisplus.exception;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author xybert
 * @description 系统异常
 * @date 2022/11/12 16:42
 */

@Getter
@Setter
public class SystemException extends RuntimeException implements ExceptionBase {

    /**
     * 异常码
     */
    private String code;
    /**
     * 中文异常信息
     */
    private String cnMsg;
    /**
     * 英文异常信息
     */
    private String enMsg;

    public SystemException() {
    }

    public SystemException(ErrorCodeBase errorCode, Object... args) {
        super(String.format(errorCode.getCnMsg(), args));
        this.code = errorCode.getCode();
        this.cnMsg = String.format(errorCode.getCnMsg(), args);
        this.enMsg = String.format(errorCode.getEnMsg(), args);
    }

    public SystemException(ErrorCodeBase errorCode, Throwable cause, Object... args) {
        super(String.format(errorCode.getCnMsg(), args), cause);
        this.code = errorCode.getCode();
        this.cnMsg = String.format(errorCode.getCnMsg(), args);
        this.enMsg = String.format(errorCode.getEnMsg(), args);
    }

    public SystemException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public SystemException(String code, String cnMsg, String enMsg, Object... args) {
        super(String.format(cnMsg, args));
        this.code = code;
        this.cnMsg = StringUtils.isBlank(cnMsg) ? "" : String.format(cnMsg, args);
        this.enMsg = StringUtils.isBlank(enMsg) ? "" : String.format(enMsg, args);
    }

    public SystemException(String code, String cnMsg, String enMsg, Throwable cause, Object... args) {
        super(String.format(cnMsg, args), cause);
        this.code = code;
        this.cnMsg = StringUtils.isBlank(cnMsg) ? "" : String.format(cnMsg, args);
        this.enMsg = StringUtils.isBlank(enMsg) ? "" : String.format(enMsg, args);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SystemException)) {
            return false;
        }
        SystemException exception = (SystemException) obj;
        return Objects.equal(this.getCode(), exception.getCode()) &&
                Objects.equal(this.getCnMsg(), exception.getCnMsg()) &&
                Objects.equal(this.getEnMsg(), exception.getEnMsg());
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object errorCode = this.getCode();
        result = result * 59 + (errorCode == null ? 43 : errorCode.hashCode());
        Object errorCnMsg = this.getCnMsg();
        result = result * 59 + (errorCnMsg == null ? 43 : errorCnMsg.hashCode());
        Object errorEnMsg = this.getEnMsg();
        result = result * 59 + (errorEnMsg == null ? 43 : errorEnMsg.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Exception(code=" + this.getCode() + ", cnMsg=" + this.getCnMsg() + ", enMsg=" + this.getEnMsg() + ")";
    }
}
