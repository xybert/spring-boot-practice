package com.xybert.springbootupload.controller;

import com.xybert.springbootupload.common.BaseResult;
import com.xybert.springbootupload.service.UploadService;
import com.xybert.springbootupload.vo.FileVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
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
    public BaseResult<FileVO> uploadSingleFile(@RequestParam("file") MultipartFile file) {
        return BaseResult.success(Collections.singletonList(uploadService.uploadSingleFile(file)));
    }

    @PostMapping("/list")
    public BaseResult<FileVO> uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        return BaseResult.success(uploadService.uploadMultipleFiles(files));
    }
}
