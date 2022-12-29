package com.xybert.springbootjpa.common.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xybert
 * @description 请求基类
 * @date 2022/12/29 16:12
 */

@Getter
@Setter
public class BaseRequest {

    /**
     * 分页查询-页码
     */
    protected Integer pageNum;

    /**
     * 分页查询-每页数量
     */
    protected Integer pageSize;
}
