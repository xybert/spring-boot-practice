package com.xybert.springbootdownload.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author xybert
 * @description 文件工具类
 * @date 2022/11/04 11:15
 */

@Slf4j
public class FileUtil {

    /**
     * 根据文件名称在文件存储目录下查询
     *
     * @param fileName 文件名称
     * @param filePath 文件路径
     * @return File
     */
    public static File findFileByName(String fileName, String filePath) {
        String path;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath() + filePath).getAbsolutePath();
        } catch (FileNotFoundException e) {
            log.error("存储目录不存在");
            return null;
        }
        File dir = new File(Objects.requireNonNull(path));
        return org.apache.commons.io.FileUtils.getFile(dir, fileName);
    }

    /**
     *文件写入zip压缩包内
     *
     * @param file 文件
     * @param zos zipOutputStream
     */
    public static void zipFile(File file, ZipOutputStream zos) {
        try(BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);
                StreamUtils.copy(bis, zos);
            }
        } catch (IOException e) {
            log.error("压缩失败：{}", file.getName());
        }
    }
}
