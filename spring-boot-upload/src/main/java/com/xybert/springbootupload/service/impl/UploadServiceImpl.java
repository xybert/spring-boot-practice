package com.xybert.springbootupload.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.xybert.springbootupload.service.UploadService;
import com.xybert.springbootupload.vo.FileVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    public FileVO uploadSingleFile(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        String rawFileName = StrUtil.subBefore(file.getOriginalFilename(), ".", true);
        String fileType = StrUtil.subAfter(file.getOriginalFilename(), ".", true);
        // 生成新文件名，避免上传相同文件导致文件名重复
        String newFileName = StrUtil.appendIfMissing(uploadFilePath, File.separator) + rawFileName + "_" +
                DateUtil.current() + "." + fileType;
        FileVO fileVO = new FileVO();
        try {
            file.transferTo(new File(newFileName));
            fileVO.setName(rawFileName);
            fileVO.setExtension(fileType);
            fileVO.setSize(getFileSize(file));
            fileVO.setTime(new Date());
        } catch (IOException e) {
            return null;
        }
        return fileVO;
    }

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件名列表
     */
    @Override
    public List<FileVO> uploadMultipleFiles(List<MultipartFile> files) {
        if (CollectionUtils.isEmpty(files)) {
            return Lists.newArrayList();
        }
        List<FileVO> fileVOList = new ArrayList<>();
        files.forEach(file -> {
            FileVO fileVO = uploadSingleFile(file);
            fileVOList.add(fileVO);
        });
        return fileVOList;
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
