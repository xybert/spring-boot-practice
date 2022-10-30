package com.xybert.springbootupload.service;

import com.xybert.springbootupload.vo.FileVO;
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
     * @return FileVO 文件信息
     */
    FileVO uploadSingleFile(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return List<FileVo> 文件信息列表
     */
    List<FileVO> uploadMultipleFiles(List<MultipartFile> files);
}
