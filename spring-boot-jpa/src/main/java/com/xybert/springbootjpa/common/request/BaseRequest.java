package com.xybert.springbootjpa.common.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xybert
 * @description 请求基类
 * @date 2022/12/29 16:12
 */

@Getter
@Setter
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -6995565666605497988L;

    /**
     * 分页查询-页码
     */
    protected Integer pageNo = 1;

    /**
     * 分页查询-每页数量
     */
    protected Integer pageSize = 10;

    /**
     * 排序类型 1-降序 2-升序
     */
    protected Integer sortType;

    /**
     * 排序字段
     */
    protected String sortField;
}
