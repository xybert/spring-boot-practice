package com.xybert.springbootmybatis.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description User 实体类
 * @author xybert
 * @date  2022/10/18 14:42
 */

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 逐渐
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 1-男 0 女
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 1-启用 2-禁用
     */
    private Integer status;

    /**
     * 角色 1-超级管理员 2-管理员 3-普通用户
     */
    private Integer role;

    /**
     * 创建时间
     */
    private DateTime createTime;

    /**
     * 更新时间
     */
    private DateTime updateTime;
}
