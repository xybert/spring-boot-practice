package com.xybert.springbootupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xybert
 * @description 文件上传业务逻辑层
 * @date 2022/10/28 16:55
 */

public interface UploadService {


    /**
     * 单文件上传
     *
     * @param file     文件
     * @return 文件名称
     */
    String uploadSingleFile(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件名列表
     */
    List<String> uploadMultipleFiles(MultipartFile[] files);
}
