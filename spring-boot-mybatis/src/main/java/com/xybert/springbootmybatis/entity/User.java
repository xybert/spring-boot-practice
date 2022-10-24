package com.xybert.springbootmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @description User 实体类
 * @author xybert
 * @date  2022/10/18 14:42
 */

@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 1-男 0 女
     */
    private Integer sex;

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
     * 状态 1-启用 0-禁用
     */
    private Integer status;

    /**
     * 角色 1-超级管理员 2-管理员 3-普通用户
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
