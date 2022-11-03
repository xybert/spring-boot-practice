package com.xybert.springbootdownload.controller;

import com.xybert.springbootdownload.common.BaseResult;
import com.xybert.springbootdownload.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

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
    public BaseResult<?> downloadSingleFile(HttpServletResponse response, @RequestParam("fileName") String fileName) {
        File file = findFileByName(fileName);
        if (file == null || FileUtils.isDirectory(file)) {
            return new BaseResult("09746", "文件不存在，请重新选择", "file does not exist");
        }
        downloadService.downloadSingleFile(response, file);
        return null;
    }

    /**
     * 根据文件名称在文件存储目录下查询
     *
     * @param fileName 文件名称
     * @return File
     */
    public File findFileByName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            log.error("请选择文件");
            return null;
        }
        String path;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath() +
                    File.separator + downloadFilePath;
        } catch (FileNotFoundException e) {
            log.error("存储目录不存在");
            return null;
        }
        File dir = new File(Objects.requireNonNull(path));
        return FileUtils.getFile(dir, fileName);
    }
}
