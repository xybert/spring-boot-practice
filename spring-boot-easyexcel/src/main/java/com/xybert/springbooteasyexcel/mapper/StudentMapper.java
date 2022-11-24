package com.xybert.springbooteasyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybert.springbooteasyexcel.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xybert
 * @description
 * @date 2022/11/24 16:55
 */

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
