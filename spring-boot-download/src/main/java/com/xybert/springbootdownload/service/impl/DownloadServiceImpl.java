package com.xybert.springbootdownload.service.impl;

import cn.hutool.http.ContentType;
import com.xybert.springbootdownload.common.BaseResult;
import com.xybert.springbootdownload.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * @author xybert
 * @description 文件下载业务处理层
 * @date 2022/10/28 17:17
 */

@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {

    @Value("${file.download.path}")
    private String downloadFilePath;

    @Override
    public void downloadSingleFile(HttpServletResponse response, String filename) {
        String path;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath() +
                    File.separator + downloadFilePath;
        } catch (FileNotFoundException e) {
            log.error("文件路径不存在");
            return;
        }
        File file = new File(path + filename);
        if (!file.exists()) {
            log.error("文件不存在：{}", filename);
            return;
        }
        response.reset();
        response.setContentType(ContentType.OCTET_STREAM.getValue());
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("文件下载失败：{}", filename);
        }
    }

    /**
     * 多文件下载
     *
     * @param response  响应体
     * @param filenames 文件名称列表
     * @return BaseResult
     */
    @Override
    public BaseResult<?> downloadMultipleFile(HttpServletResponse response, List<String> filenames) {
        return null;
    }
}
