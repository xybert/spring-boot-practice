package com.xybert.springbootdownload.service.impl;

import cn.hutool.http.ContentType;
import com.xybert.springbootdownload.service.DownloadService;
import com.xybert.springbootdownload.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author xybert
 * @description 文件下载业务处理层
 * @date 2022/10/28 17:17
 */

@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {

    @Override
    public void downloadSingleFile(HttpServletResponse response, File file) {
        String fileName = file.getName();
        response.setContentType(ContentType.OCTET_STREAM.getValue());
        response.setContentLengthLong(file.length());
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" +
                    URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"));
            StreamUtils.copy(bis, response.getOutputStream());
        } catch (IOException e) {
            log.error("文件下载失败：{}", fileName);
        }
    }

    /**
     * 多文件下载
     *
     * @param response  响应体
     * @param files 文件列表
     */
    @Override
    public void downloadMultipleFile(HttpServletResponse response, List<File> files) {
        String zipName = "data.zip";
        try(ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            response.setContentType(ContentType.MULTIPART.getValue());
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" +
                    URLEncoder.encode(zipName, "UTF-8").replaceAll("\\+", "%20"));
            for (File file : files) {
                FileUtil.zipFile(file, zos);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
