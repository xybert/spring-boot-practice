package com.xybert.springbooteasyexcel.constant;

import com.xybert.springbooteasyexcel.exception.ErrorCodeBase;
import lombok.Getter;

/**
 * @author xybert
 * @description 状态码规约
 * @date 2022/11/24 13:42
 */

@Getter
public enum SystemResultCode implements ErrorCodeBase {

    /**
     * 系统异常
     */
    SYSTEM_NOT_HANDLER_EXCEPTION("09999", "系统内部错误", "system error"),
    EXCEL_PARSE_ERROR("29001", "Excel文件解析失败", "excel parse error"),
    NO_DATA_EXIST("29002", "数据不存在，无法导出", "no data exist"),
    DATA_EXPORT_FAIL("29003", "数据导出失败", "data export failed"),
    ;

    /**
     * 状态码
     */
    final String code;
    /**
     * 中文提示信息
     */
    final String cnMsg;
    /**
     * 英文提示信息
     */
    final String enMsg;

    SystemResultCode(String code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }
}
