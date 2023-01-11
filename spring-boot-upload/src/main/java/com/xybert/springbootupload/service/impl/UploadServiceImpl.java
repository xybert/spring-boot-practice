package com.xybert.springbootupload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.BaseResult;
import com.xybert.springbootupload.enums.ExceptionEnum;
import com.xybert.springbootupload.service.UploadService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xybert
 * @description 文件上传业务逻辑层
 * @date 2022/10/28 17:00
 */

@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.upload.path}")
    private String uploadFilePath;

    /**
     * 单文件上传
     *
     * @param file 文件
     * @return 文件名称
     */
    @Override
    public BaseResult uploadSingleFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BaseException(ExceptionEnum.EMPTY_FILE);
        }
        String rawFileName = StrUtil.subBefore(file.getOriginalFilename(), ".", true);
        String fileType = StrUtil.subAfter(file.getOriginalFilename(), ".", true);

        /// 生成新文件名，避免上传相同文件导致文件名重复
        /// 上传至外部目录
        /// String newFileName = StrUtil.appendIfMissing(uploadFilePath, File.separator) + rawFileName + "_" +
        ///                 DateUtil.current() + "." + fileType;

        // 上传至static目录下
        String newFileName;
        try {
            newFileName = ResourceUtils.getURL("classpath:").getPath() + uploadFilePath +
                    rawFileName + "_" + DateUtil.current() + "." + fileType;
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.PATH_NOT_EXIST);
        }
        File dest = new File(newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.UPLOAD_FAIL);
        }
        return BaseResult.success();
    }

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件名列表
     */
    @Override
    public BaseResult uploadMultipleFiles(List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return BaseResult.fail();
        }
        files.forEach(this::uploadSingleFile);
        return BaseResult.success();
    }
}
