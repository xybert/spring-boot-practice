package com.xybert.springbootdownload.service;

import com.xybert.springbootdownload.common.BaseResult;

import javax.servlet.http.HttpServletResponse;
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
     * @param filename 文件名称
     */
    void downloadSingleFile(HttpServletResponse response, String filename);

    /**
     * 多文件下载
     *
     * @param response 响应体
     * @param filenames 文件名称列表
     * @return BaseResult
     */
    BaseResult<?> downloadMultipleFile(HttpServletResponse response, List<String> filenames);
}
