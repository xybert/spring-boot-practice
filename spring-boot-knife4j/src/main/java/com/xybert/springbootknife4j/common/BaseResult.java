package com.xybert.springbootknife4j.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.xybert.springbootknife4j.exception.ErrorCodeBase;
import com.xybert.springbootknife4j.exception.ExceptionBase;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author xybert
 * @description 统一返回结果
 * @date 2022/11/11 10:40
 */

public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 3770714949307073998L;

    BaseResultCode resultCode;
    Result<T> result;

    public BaseResult() {
        this.resultCode = BaseResultCode.defaultResult();
        this.result = new Result<>(Collections.emptyList());
    }

    public BaseResult(BaseResultCode resultCode, Result<? extends T> result) {
        this.resultCode = resultCode;
        this.result = (Result<T>) result;
    }

    /**
     * 默认的状态码无法满足的时候，支持自定义状态码
     *
     * @param code 状态码
     * @param cnMsg 中文信息
     * @param enMsg 英文信息
     * @param result 返回结果
     */
    public BaseResult(String code, String cnMsg, String enMsg, Result<? extends T> result) {
        this.resultCode = new BaseResultCode(code, cnMsg, enMsg);
        this.result = (Result<T>) result;
    }

    /**
     * 默认的状态码无法满足的时候，支持自定义状态码
     *
     * @param code 状态码
     * @param cnMsg 中文信息
     * @param enMsg 英文信息
     */
    public BaseResult(String code, String cnMsg, String enMsg) {
        this.resultCode = new BaseResultCode(code, cnMsg, enMsg);
        this.result = new Result<>(Collections.emptyList());
    }

    /**
     * 构造函数
     *
     * @param errorCode errorCode
     */
    public BaseResult(ErrorCodeBase errorCode) {
        this.resultCode = new BaseResultCode(errorCode);
        this.result = new Result<>(Collections.emptyList());
    }

    /**
     * 构造函数
     *
     * @param baseResultCode errorCode
     */
    public BaseResult(BaseResultCode baseResultCode) {
        this.resultCode = baseResultCode;
        this.result = new Result<>(Collections.emptyList());
    }

    /**
     * 构造函数
     *
     * @param exception exception
     */
    protected BaseResult(ExceptionBase exception) {
        this.resultCode = new BaseResultCode(exception.getCode(), exception.getCnMsg(), exception.getEnMsg());
        this.result = new Result<>(Collections.emptyList());
    }

    /**
     * 用户自定义传入枚举类，枚举类中必须有code,cnMsg,enMsg这几个属性，并且实现了set/get方法
     *
     * @param t 枚举类
     * @param result 返回结果
     */
    public BaseResult(Enum t, Result<? extends T> result) {
        Method[] methods = t.getClass().getDeclaredMethods();
        boolean errorHappened = false;
        BaseResultCode resultCode = new BaseResultCode();
        for (Method method : methods) {
            try {
                if ("getCode".equals(method.getName())) {
                    resultCode.setCode(method.invoke(t).toString());
                    continue;
                }
                if ("getCnMsg".equals(method.getName())) {
                    resultCode.setCnMsg(method.invoke(t).toString());
                    continue;
                }
                if ("getEnMsg".equals(method.getName())) {
                    resultCode.setEnMsg(method.invoke(t).toString());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                this.resultCode = BaseResultCode.sysError();
                errorHappened = true;
                break;
            }
        }
        if (!errorHappened) {
            this.result = (Result<T>) result;
            this.resultCode = resultCode;
        }
    }


    /**
     * 用户自定义传入枚举类，枚举类中必须有code,cnMsg,enMsg这几个属性，并且实现了set/get方法
     *
     * @param t 枚举类
     */
    public BaseResult(Enum t) {
        Method[] methods = t.getClass().getDeclaredMethods();
        BaseResultCode resultCode = new BaseResultCode();
        for (Method method : methods) {
            try {
                if ("getCode".equals(method.getName())) {
                    resultCode.setCode(method.invoke(t).toString());
                    continue;
                }
                if ("getCnMsg".equals(method.getName())) {
                    resultCode.setCnMsg(method.invoke(t).toString());
                    continue;
                }
                if ("getEnMsg".equals(method.getName())) {
                    resultCode.setEnMsg(method.invoke(t).toString());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                this.resultCode = BaseResultCode.sysError();
                return;
            }
        }

        this.resultCode = resultCode;
    }

    /**
     * 成功返回
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> success() {
        return new BaseResult<>("00000", "成功", "success");
    }

    /**
     * 成功返回，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> success(Result<? extends T> result) {
        return new BaseResult<>(BaseResultCode.success(), result);
    }

    /**
     * 成功返回，支持自定义data字段
     *
     * @param data 自定义数据
     * @return BaseResult
     */
    public static <T> BaseResult<T> success(List<? extends T> data) {
        return new BaseResult<>(BaseResultCode.success(), new Result<>(data));
    }

    /**
     * 成功返回，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> success(List<? extends T> result, Long totalCount, Long pageSize) {
        return new BaseResult<>(BaseResultCode.success(), new Result<>(totalCount, pageSize, result));
    }

    /**
     * 系统错误返回
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> sysError() {
        return new BaseResult<>("09999", "系统内部错误", "system error");
    }

    /**
     * 系统错误返回，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> sysError(Result<? extends T> result) {
        return new BaseResult<>(BaseResultCode.sysError(), result);
    }

    /**
     * 系统错误返回，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> sysError(List<? extends T> result) {
        return new BaseResult<>(BaseResultCode.sysError(), new Result<>(result));
    }

    /**
     * 系统错误返回，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> sysError(List<? extends T> result, Long totalCount, Long pageSize) {
        return new BaseResult<>(BaseResultCode.sysError(), new Result<>(totalCount, pageSize, result));
    }

    /**
     * 网络异常错误
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> networkError() {
        return new BaseResult<>("09998", "网络异常", "network error");
    }

    /**
     * 网络异常错误，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> networkError(Result<? extends T> result) {
        return new BaseResult<>(BaseResultCode.networkError(), result);
    }

    /**
     * 网络异常错误，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> networkError(List<? extends T> result) {
        return new BaseResult<>(BaseResultCode.networkError(), new Result<>(result));
    }

    /**
     * 网络异常错误，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> networkError(List<? extends T> result, Long totalCount, Long pageSize) {
        return new BaseResult<>(BaseResultCode.networkError(), new Result<>(totalCount, pageSize, result));
    }

    /**
     * 参数错误异常
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> paramError() {
        return new BaseResult<>("09997", "参数错误", "params error");
    }

    /**
     * 参数错误异常，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> paramError(Result<? extends T> result) {
        return new BaseResult<>(BaseResultCode.paramError(), result);
    }

    /**
     * 参数错误异常，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> paramError(List<? extends T> result) {
        return new BaseResult<>(BaseResultCode.paramError(), new Result<>(result));
    }

    /**
     * 参数错误异常，支持自定义data字段
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> paramError(List<? extends T> result, Long totalCount, Long pageSize) {
        return new BaseResult<>(BaseResultCode.paramError(), new Result<>(totalCount, pageSize, result));
    }

    /**
     * 通用失败
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail(Result<? extends T> result) {
        return new BaseResult<>(BaseResultCode.fail(), result);
    }

    /**
     * 通用失败
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail(List<? extends T> result) {
        return new BaseResult<>(BaseResultCode.fail(), new Result<>(result));
    }

    /**
     * 通用失败
     *
     * @param result 返回结果
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail(List<? extends T> result, Long totalCount, Long pageSize) {
        return new BaseResult<>(BaseResultCode.fail(), new Result<>(totalCount, pageSize, result));
    }

    /**
     * 通用失败
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> fail() {
        return new BaseResult<>("09996", "操作失败", "operate fail");
    }

    /**
     * 系统错误返回
     *
     * @return BaseResult
     */
    public static <T> BaseResult<T> noPermission() {
        return new BaseResult<>(BaseResultCode.noPermission());
    }

    /**
     * 异常转换成BaseResult
     *
     * @param exception exception
     * @return BaseResult
     */
    public static <T> BaseResult<T> fromException(ExceptionBase exception) {
        if (StringUtils.isBlank(exception.getCode())) {
            return sysError();
        }

        return new BaseResult<>(exception);
    }

    public BaseResultCode getResultCode() {
        return resultCode;
    }

    @JsonSetter
    public void setResultCode(BaseResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result<T> getResult() {
        return result;
    }

    public void setResult(Result<T> data) {
        this.result = data;
    }

    @Override
    public String toString() {
        return toJson();
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public void setResultCode(Enum t) {
        Method[] methods = t.getClass().getDeclaredMethods();
        BaseResultCode resultCode = new BaseResultCode();
        for (Method method : methods) {
            try {
                if ("getCode".equals(method.getName())) {
                    resultCode.setCode(method.invoke(t).toString());
                    continue;
                }
                if ("getCnMsg".equals(method.getName())) {
                    resultCode.setCnMsg(method.invoke(t).toString());
                    continue;
                }
                if ("getEnMsg".equals(method.getName())) {
                    resultCode.setEnMsg(method.invoke(t).toString());
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                this.resultCode = BaseResultCode.sysError();
                break;
            }
        }
    }

    public void setResultCode(String code, String cnMsg, String enMsg) {
        this.resultCode = new BaseResultCode(code, cnMsg, enMsg);
    }
}
