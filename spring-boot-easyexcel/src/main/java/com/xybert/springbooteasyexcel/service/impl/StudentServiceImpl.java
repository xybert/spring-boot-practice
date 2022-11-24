package com.xybert.springbooteasyexcel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xybert.springbooteasyexcel.entity.Student;
import com.xybert.springbooteasyexcel.mapper.StudentMapper;
import com.xybert.springbooteasyexcel.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xybert
 * @description Student业务逻辑实现类
 * @date 2022/11/24 17:00
 */

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;
}
