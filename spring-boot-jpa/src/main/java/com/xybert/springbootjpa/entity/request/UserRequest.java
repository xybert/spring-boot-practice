package com.xybert.springbootjpa.entity.request;

import com.xybert.springbootjpa.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xybert
 * @description User请求参数
 * @date 2022/12/29 16:08
 */

@Getter
@Setter
public class UserRequest extends BaseRequest {

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 状态
     */
    private Integer status;
}
