package com.xybert.springbootdownload.service.impl;

import com.xybert.springbootdownload.service.DownloadService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xybert
 * @description 文件下载业务处理层
 * @date 2022/10/28 17:17
 */

@Service
public class DownloadServiceImpl implements DownloadService {
    @Override
    public boolean downloadSingleFile(HttpServletResponse response, String filename) {
        return false;
    }
}
