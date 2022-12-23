package com.xybert.springbootmybatisplus.common;

import com.alibaba.fastjson.JSONObject;
import com.xybert.springbootmybatisplus.exception.ErrorCodeBase;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xybert
 * @description 返回结果代码
 * @date 2022/10/30 21:58
 */

@Data
public class BaseResultCode implements Serializable {

    private static final long serialVersionUID = 58015867066694984L;

    //状态码
    String code;

    //中文返回信息
    String cnMsg;

    //英文返回信息
    String enMsg;

    /**
     * 构造函数
     */
    public BaseResultCode() {
    }

    public BaseResultCode(String code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }

    /**
     * 构造函数
     *
     * @param errorCode errorCode
     */
    public BaseResultCode(ErrorCodeBase errorCode) {
        this.code = errorCode.getCode();
        this.cnMsg = errorCode.getCnMsg();
        this.enMsg = errorCode.getEnMsg();
    }

    public static BaseResultCode success() {
        return new BaseResultCode("00000", "成功", "success");
    }

    public static BaseResultCode fail() {
        return new BaseResultCode("09996", "操作失败", "operate fail");
    }

    public static BaseResultCode paramError() {
        return new BaseResultCode("09997", "参数错误", "params error");
    }

    public static BaseResultCode networkError() {
        return new BaseResultCode("09998", "网络异常", "network error");
    }

    public static BaseResultCode sysError() {
        return new BaseResultCode("09999", "系统内部错误", "system error");
    }

    public static BaseResultCode noPermission() {
        return new BaseResultCode("08207", "权限不足", "no permission");
    }

    public static BaseResultCode defaultResult() {
        return new BaseResultCode("00001", "", "");
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("cnMsg", cnMsg);
        object.put("enMsg", enMsg);
        return object.toJSONString();
    }
}
