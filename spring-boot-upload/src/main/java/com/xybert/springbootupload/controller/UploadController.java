package com.xybert.springbootupload.controller;

import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootupload.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xybert
 * @description UploadFile 文件上传
 * @date 2022/10/26 10:54
 */

@RestController
@RequestMapping("/file")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/single")
    public BaseResult uploadSingleFile(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadSingleFile(file);
    }

    @PostMapping("/list")
    public BaseResult uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        return uploadService.uploadMultipleFiles(files);
    }
}
