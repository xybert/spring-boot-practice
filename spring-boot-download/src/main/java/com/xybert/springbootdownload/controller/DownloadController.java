package com.xybert.springbootdownload.controller;

import com.xybert.springbootdownload.service.DownloadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xybert
 * @description 文件下载
 * @date 2022/10/28 16:47
 */

@RestController
@RequestMapping("/file")
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @GetMapping("/single")
    public void downloadSingleFile(HttpServletResponse response, @RequestParam("filename") String filename) {
        downloadService.downloadSingleFile(response, filename);
    }
}
