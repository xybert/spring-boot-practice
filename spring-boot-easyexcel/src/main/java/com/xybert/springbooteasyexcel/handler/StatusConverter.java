package com.xybert.springbooteasyexcel.handler;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.util.Objects;

/**
 * @author xybert
 * @description 状态转换器
 * @date 2022/12/23 17:54
 */

public class StatusConverter implements Converter<Integer> {

    public static final String ENABLE = "启用";
    public static final String DISABLE = "禁用";

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) {
        return Objects.equals(ENABLE, context.getReadCellData().getStringValue()) ? 1 : 0;
    }

    @Override
    public WriteCellData convertToExcelData(WriteConverterContext<Integer> context) {
        return new WriteCellData(Objects.equals(1, context.getValue()) ? ENABLE : DISABLE);
    }
}
