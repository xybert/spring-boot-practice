package com.xybert.springbootjpa.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xybert
 * @description UserDto
 * @date 2022/12/29 11:35
 */

@Getter
@Setter
public class UserDto {

    /**
     * 用户名
     */
    private String account;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private Integer status;
}
