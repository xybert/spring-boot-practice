package com.xybert.springbootupload.controller;

import com.xybert.springbootupload.util.FileUploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xybert
 * @description UploadFile 文件上传
 * @date 2022/10/26 10:54
 */

@RestController
@RequestMapping("file")
public class FileUploadController {

    @PostMapping("/img")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return FileUploadUtils.uploadImage(file) ? "success" : "fail";
    }
}
