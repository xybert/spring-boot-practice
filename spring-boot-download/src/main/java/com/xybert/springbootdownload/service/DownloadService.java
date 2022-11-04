package com.xybert.springbootdownload.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author xybert
 * @description 文件下载业务处理层
 * @date 2022/10/28 17:16
 */

public interface DownloadService {

    /**
     * 单文件下载
     *
     * @param response 响应体
     * @param file 文件
     */
    void downloadSingleFile(HttpServletResponse response, File file);

    /**
     * 文件批量下载
     * 多个文件压缩成一个zip包进行下载
     *
     * @param response 响应体
     * @param files 文件列表
     */
    void downloadMultipleFile(HttpServletResponse response, List<File> files);
}
