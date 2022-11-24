package com.xybert.springbooteasyexcel.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.xybert.springbooteasyexcel.handler.SexConverterHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xybert
 * @description 学生信息
 * @date 2022/11/24 15:10
 */

@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoVo {

    @ExcelProperty(value = "序号")
    private Long id;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "学号")
    private Long stuNo;

    @ExcelProperty(value = "班级")
    private String clazz;

    @ExcelProperty(value = "性别", converter = SexConverterHandler.class)
    private Integer sex;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "监护人")
    private String guardian;

    @ExcelProperty(value = "联系电话")
    private String tel;

    @ExcelProperty(value = "邮箱")
    private String email;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "录入时间")
    private Date createTime;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    private Boolean deleted;
}
