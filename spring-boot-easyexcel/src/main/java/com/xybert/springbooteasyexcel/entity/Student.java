package com.xybert.springbooteasyexcel.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xybert
 * @description 学生实体类
 * @date 2022/11/24 14:46
 */

@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "student")
public class Student {

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String name;

    /**
     * 学号
     */
    @TableField(value = "stuNo", insertStrategy = FieldStrategy.NOT_NULL)
    private Long stuNo;

    /**
     * 班级
     */
    @TableField(value = "clazz", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String clazz;

    /**
     * 性别
     */
    @TableField(value = "sex", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer sex;

    /**
     * 年龄
     */
    @TableField(value = "age", insertStrategy = FieldStrategy.NOT_NULL)
    private Integer age;

    /**
     * 监护人
     */
    @TableField(value = "guardian", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String guardian;

    /**
     * 联系电话
     */
    @TableField(value = "tel", insertStrategy = FieldStrategy.NOT_EMPTY)
    private String tel;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 数学成绩
     */
    @TableField(value = "chinese")
    private Float chinese;

    /**
     * 数学成绩
     */
    @TableField(value = "math")
    private Float math;

    /**
     * 英语成绩
     */
    @TableField(value = "english")
    private Float english;

    /**
     * 物理成绩
     */
    @TableField(value = "physics")
    private Float physics;

    /**
     * 化学成绩
     */
    @TableField(value = "chemistry")
    private Float chemistry;

    /**
     * 生物成绩
     */
    @TableField(value = "biology")
    private Float biology;

    /**
     * 总分
     */
    @TableField(value = "score")
    private Float score;

    /**
     * 班级排名
     */
    @TableField(value = "clazz_rank")
    private Integer clazzRank;

    /**
     * 年级排名
     */
    @TableField(value = "grade_rank")
    private Integer gradeRank;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Boolean deleted;

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
