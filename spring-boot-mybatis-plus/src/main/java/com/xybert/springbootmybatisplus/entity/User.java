package com.xybert.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description User 实体类
 * @author xybert
 * @date 2022/10/24 17:06
 */

@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {

    /**
     * 逐渐
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 性别 1-男 0 女
     */
    @TableField(value = "sex", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(value = "age", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer age;

    /**
     * 联系电话
     */
    @TableField(value = "tel")
    private String tel;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 状态 1-启用 0-禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 角色 1-超级管理员 2-管理员 3-普通用户
     */
    @TableField(value = "role")
    private Integer role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
