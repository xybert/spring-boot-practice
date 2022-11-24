package com.xybert.springbooteasyexcel.handler;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.util.Objects;

/**
 * @author xybert
 * @description 性别转换器
 * @date 2022/11/24 17:19
 */

public class SexConverterHandler implements Converter<Integer> {

    private final static String MAN = "男";
    private final static String WOMAN = "女";

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
        return MAN.equals(context.getReadCellData().getStringValue()) ? 1 : 0;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) {
        return new WriteCellData(Objects.equals(context.getValue(), 1) ? MAN : WOMAN);
    }
}
