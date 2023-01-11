package com.xybert.springbootdownload.controller;

import com.xybert.springbootdownload.enums.ExceptionEnum;
import com.xybert.springbootdownload.service.DownloadService;
import com.xybert.springbootdownload.util.FileUtil;
import com.xybert.springbootexception.exception.BaseException;
import com.xybert.springbootexception.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xybert
 * @description 文件下载
 * @date 2022/10/28 16:47
 */

@Slf4j
@RestController
@RequestMapping("/file")
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @Value("${file.download.path}")
    private String downloadFilePath;

    @GetMapping("/single")
    public BaseResult downloadSingleFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        File file = FileUtil.findFileByName(fileName, downloadFilePath);
        if (file == null || FileUtils.isDirectory(file)) {
            throw new BaseException(ExceptionEnum.FILE_NOT_EXIST);
        }
        downloadService.downloadSingleFile(response, file);
        return null;
    }

    @GetMapping("/multiple")
    public BaseResult downloadMultipleFile(HttpServletResponse response, @RequestParam("fileNames") String fileNames) {
        // fileNames 文件名称列表，由多个文件名称字符串组成，中间以英文逗号（,）分隔
        List<String> fileNameList = Arrays.asList(fileNames.split(","));
        List<File> files = new ArrayList<>();
        fileNameList.forEach(fileName -> {
            File file = FileUtil.findFileByName(fileName, downloadFilePath);
            if (file != null && !FileUtils.isDirectory(file)) {
                files.add(file);
            } else {
                log.error("文件不存在：{}", fileName);
            }
        });
        if (CollectionUtils.isEmpty(files)) {
            throw new BaseException(ExceptionEnum.FILE_NOT_EXIST);
        }
        downloadService.downloadMultipleFile(response, files);
        return null;
    }
}
