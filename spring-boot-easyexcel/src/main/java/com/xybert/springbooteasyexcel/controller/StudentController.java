package com.xybert.springbooteasyexcel.controller;

import com.xybert.springbooteasyexcel.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xybert
 * @description Student 接口层
 * @date 2022/11/24 17:14
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;
}
