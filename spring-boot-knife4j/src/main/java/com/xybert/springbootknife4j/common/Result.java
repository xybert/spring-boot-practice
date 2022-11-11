package com.xybert.springbootknife4j.common;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author xybert
 * @description 返回结果
 * @date 2022/11/11 10:41
 */

@Data
public class Result<T> {

    Long totalCount;
    Long pageSize;
    List<T> data;

    public Result() {
    }

    public Result(Long totalCount, Long pageSize, List<T> data) {
        this.totalCount = Objects.isNull(totalCount) ? 0 : totalCount;
        this.pageSize = Objects.isNull(pageSize) ? 0 : pageSize;
        this.data = data;
    }

    public Result(List<T> data) {
        this.totalCount = 0L;
        this.pageSize = 0L;
        this.data = data;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = Objects.isNull(totalCount) ? 0 : totalCount;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = Objects.isNull(pageSize) ? 0 : pageSize;
    }
}
