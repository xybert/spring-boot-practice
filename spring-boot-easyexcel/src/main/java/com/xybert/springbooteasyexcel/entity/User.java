package com.xybert.springbooteasyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xybert.springbooteasyexcel.handler.SexConverter;
import com.xybert.springbooteasyexcel.handler.StatusConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xybert
 * @description User实体类
 * @date 2022/12/23 10:11
 */

@Component
@Data
@TableName(value = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "account", insertStrategy = FieldStrategy.NOT_EMPTY)
    @ExcelProperty(index = 1)
    private String account;

    /**
     * 性别 1-男 0 女
     */
    @TableField(value = "sex")
    @ExcelProperty(index = 2, converter = SexConverter.class)
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ExcelProperty(index = 3)
    private Integer age;

    /**
     * 联系电话
     */
    @TableField(value = "tel")
    @ExcelProperty(index = 4)
    private String tel;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ExcelProperty(index = 5)
    private String email;

    /**
     * 状态 1-启用 0-禁用
     */
    @TableField(value = "status")
    @ExcelProperty(index = 6, converter = StatusConverter.class)
    private Integer status;

    /**
     * 创建人
     */
    @TableField(value = "create_user", insertStrategy = FieldStrategy.NOT_NULL)
    private Long createUser;

    /**
     * 更新人
     */
    @TableField(value = "update_user")
    private Long updateUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除字段
     */
    @TableField(value = "deleted", insertStrategy = FieldStrategy.NOT_NULL)
    @TableLogic
    @ExcelIgnore
    private Integer deleted;
}
