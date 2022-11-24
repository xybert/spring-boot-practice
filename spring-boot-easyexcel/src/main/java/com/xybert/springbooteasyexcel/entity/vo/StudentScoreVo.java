package com.xybert.springbooteasyexcel.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * @author xybert
 * @description 学生成绩
 * @date 2022/11/24 15:54
 */

@Component
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentScoreVo {

    @ExcelProperty(value = "序号")
    private Long id;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "学号")
    private Long stuNo;

    @ExcelProperty(value = "班级")
    private String clazz;

    @ExcelProperty(value = {"各科成绩", "语文"})
    private Float chinese;

    @ExcelProperty(value = {"各科成绩", "数学"})
    private Float math;

    @ExcelProperty(value = {"各科成绩", "英语"})
    private Float english;

    @ExcelProperty(value = {"各科成绩", "物理"})
    private Float physics;

    @ExcelProperty(value = {"各科成绩", "化学"})
    private Float chemistry;

    @ExcelProperty(value = {"各科成绩", "生物"})
    private Float biology;

    @ExcelProperty(value = "总分")
    private Float score;

    @ExcelProperty(value = "班级排名")
    private Integer clazzRank;

    @ExcelProperty(value = "年级排名")
    private Integer gradeRank;

    private Boolean deleted;
}
