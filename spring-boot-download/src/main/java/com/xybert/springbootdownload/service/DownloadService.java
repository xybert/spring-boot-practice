package com.xybert.springbootdownload.service;

import javax.servlet.http.HttpServletResponse;

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
     * @param filename 文件名称
     * @return stream
     */
    boolean downloadSingleFile(HttpServletResponse response, String filename);
}
