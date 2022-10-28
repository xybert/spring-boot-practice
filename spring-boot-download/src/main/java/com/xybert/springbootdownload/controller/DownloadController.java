package com.xybert.springbootdownload.controller;

import com.xybert.springbootdownload.service.DownloadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xybert
 * @description 文件下载
 * @date 2022/10/28 16:47
 */

@RestController
@RequestMapping("")
public class DownloadController {

    @Resource
    private DownloadService downloadService;

    @GetMapping("/file")
    public String downloadSingleFile(HttpServletResponse response, @RequestParam("filename") String filename) {
        return downloadService.downloadSingleFile(response, filename) ? "success" : "fail";
    }
}
