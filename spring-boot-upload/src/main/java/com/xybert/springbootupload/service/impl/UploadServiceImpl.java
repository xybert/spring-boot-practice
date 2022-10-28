package com.xybert.springbootupload.service.impl;

import com.xybert.springbootupload.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xybert
 * @description 文件上传业务逻辑层
 * @date 2022/10/28 17:00
 */

@Service
public class UploadServiceImpl implements UploadService {
    /**
     * 单文件上传
     *
     * @param file 文件
     * @return 文件名称
     */
    @Override
    public String uploadSingleFile(MultipartFile file) {
        return null;
    }

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件名列表
     */
    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        return null;
    }
}
