package com.xybert.springbooteasyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author xybert
 * @description EasyExcel监听器
 * @date 2022/11/25 14:07
 */

@Slf4j
@Getter
public class ExcelListener<T> extends AnalysisEventListener<T> {

    /**
     * 每次缓存的数据量
     */
    // private final static int BATCH_SIZE = 500;

    private final List<T> rowData = Lists.newArrayList();

    /**
     * 缓存数据
     *
     * 数据量较大时，可对数据进行批量处理
     * 例如，存到数据库中，然后清空缓存
     */
    // private List<T> cacheData = ListUtils.newArrayListWithCapacity(BATCH_SIZE);

    @Override
    public void invoke(T data, AnalysisContext context) {
        rowData.add(data);
        // 每当数据量比较大时，每达到BATCH_SIZE，存入数据库，清空列表，防止内存占用过多
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 若采用批量处理，解析完数据后，还需录入剩余数据
        log.info("成功录入所有数据，数据量为：{}", rowData.size());
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
            log.error(Arrays.toString(exception.getStackTrace()));
        }
    }

    /**
     * 获取解析的Excel数据
     *
     * @return List<T>
     */
    public List<T> getRowData() {
        return rowData;
    }
}
