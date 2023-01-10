package com.xybert.springbootjpa.entity.request;

import com.xybert.springbootjpa.common.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author xybert
 * @description User请求参数
 * @date 2022/12/29 16:08
 */

@Getter
@Setter
public class UserRequest extends BaseRequest {

    /**
     * 用户名
     */
    private String account;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 起始年龄
     */
    private Integer beginAge;

    /**
     * 结束年龄
     */
    private Integer endAge;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 起始创建时间
     */
    private Date beginTime;

    /**
     * 结束创建时间
     */
    private Date endTime;
}
