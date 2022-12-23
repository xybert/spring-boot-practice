package com.xybert.springbootupload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.xybert.springbootupload.common.BaseResult;
import com.xybert.springbootupload.service.UploadService;
import com.xybert.springbootupload.vo.FileVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    public BaseResult<FileVO> uploadSingleFile(MultipartFile file) {
        if (file.isEmpty()) {
            return new BaseResult("06239", "文件为空", "empty file");
        }
        String rawFileName = StrUtil.subBefore(file.getOriginalFilename(), ".", true);
        String fileType = StrUtil.subAfter(file.getOriginalFilename(), ".", true);

        // 生成新文件名，避免上传相同文件导致文件名重复
        /// 上传至外部目录
        /// String newFileName = StrUtil.appendIfMissing(uploadFilePath, File.separator) + rawFileName + "_" +
        ///                 DateUtil.current() + "." + fileType;

        // 上传至static目录下
        String newFileName;
        try {
            newFileName = ResourceUtils.getURL("classpath:").getPath() + uploadFilePath +
                    rawFileName + "_" + DateUtil.current() + "." + fileType;
        } catch (FileNotFoundException e) {
            return new BaseResult("06579", "文件未找到", "file not found");
        }
        File dest = new File(newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FileVO fileVO = new FileVO();
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
            fileVO.setName(rawFileName);
            fileVO.setExtension(fileType);
            fileVO.setSize(getFileSize(file));
            fileVO.setTime(new Date());
        } catch (IOException e) {
            return new BaseResult("06949", "文件上传失败", "file upload failed");
        }
        return BaseResult.success(Collections.singletonList(fileVO));
    }

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件名列表
     */
    @Override
    public BaseResult<FileVO> uploadMultipleFiles(List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return BaseResult.fail(Lists.newArrayList());
        }
        List<FileVO> fileVOList = new ArrayList<>();
        files.forEach(file -> fileVOList.addAll(uploadSingleFile(file).getResult().getData()));
        return BaseResult.success(fileVOList);
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小字符串
     */
    public static String getFileSize(MultipartFile file) {
        final long kb = 1024;
        final long mb = kb * 1024;
        long fileSize = file.getSize();
        if (fileSize >= mb) {
            return new BigDecimal(fileSize / mb).setScale(2, RoundingMode.HALF_UP).doubleValue() + "MB";
        } else if (fileSize >= kb) {
            return new BigDecimal(fileSize / kb).setScale(2, RoundingMode.HALF_UP).doubleValue() + "KB";
        } else {
            return new BigDecimal(fileSize).setScale(2, RoundingMode.HALF_UP).doubleValue() + "B";
        }
    }
}
