package com.xybert.springbootupload.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.xybert.springbootupload.enums.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author xybert
 * @description UploadFileUtils 文件上传工具类
 * @date 2022/10/26 13:55
 */

@Slf4j
@Component
public class FileUploadUtils {

    private static final String UPLOAD_FILE_PATH = "D:\\";
    private static final String DOWNLOAD_FILE_PATH = "C:\\Users\\26767\\Pictures";

    /**
     * 文件上传
     *
     * @param file 文件
     * @return boolean
     */
    public static boolean uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("文件内容为空，请选择文件");
            return false;
        }
        String newFileName = generateFileName(file);
        try {
            file.transferTo(new File(newFileName));
        } catch (IOException e) {
            log.error("文件上传失败，文件：{}，上传路径：{}", file.getOriginalFilename(), UPLOAD_FILE_PATH);
            return false;
        }
        log.info("文件上传成功，文件：{}", newFileName);
        return true;
    }

    /**
     * 图片上传
     *
     * @param file 文件
     * @return boolean
     */
    public static boolean uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("文件内容为空，请选择文件");
            return false;
        }
        String newFileName = generateFileName(file);
        if (!isImage(file) && !checkImageFormat(file)) {
            log.error("文件不符合系统要求，文件：{}", file.getOriginalFilename());
            return false;
        }
        try {
            file.transferTo(new File(newFileName));
        } catch (IOException e) {
            log.error("文件上传失败，文件：{}，上传路径：{}", file.getOriginalFilename(), UPLOAD_FILE_PATH);
            return false;
        }
        try {
            BufferedImage image = ImageIO.read(Files.newInputStream(Paths.get(newFileName)));
            log.info("图片大小：{}，分辨率：{}×{}", getFileSize(file), image.getWidth(), image.getHeight());
        } catch (IOException e) {
            log.error("");
        }
        log.info("文件上传成功，文件：{}", newFileName);
        return true;
    }

    public static boolean downloadFile(HttpServletResponse response, String filename) {
        // 检查文件是否存在
        if (filename == null) {
            return false;
        }
        String filePath = StrUtil.appendIfMissing(DOWNLOAD_FILE_PATH, File.separator) + filename;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            response.setContentType("application/force-download");
            response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(filename, "UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * 校验上传文件是否为图片
     *
     * @param file 文件
     * @return boolean
     */
    public static boolean isImage(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        try {
            Image img = ImageIO.read(file.getInputStream());
            return img != null && img.getWidth(null) > 0 && img.getHeight(null) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验上传文件格式是否符合系统要求
     *
     * @param file 文件
     * @return boolean
     */
    public static boolean checkImageFormat(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        String filename = file.getOriginalFilename();
        String fileTypeName = FileTypeEnum.getByFileTypeName(filename).getFileTypeName();
        return fileTypeName.equalsIgnoreCase(FileTypeEnum.PNG.getFileTypeName()) || fileTypeName.equalsIgnoreCase(FileTypeEnum.JPEG.getFileTypeName());
    }

    /**
     * 生成文件名
     *
     * @param file 文件
     * @return newFileName 新文件名（绝对路径）
     */
    public static String generateFileName(MultipartFile file) {
        String rawFileName = StrUtil.subBefore(file.getOriginalFilename(), ".", true);
        String fileType = StrUtil.subAfter(file.getOriginalFilename(), ".", true);
        // 生成新的文件名称
        return StrUtil.appendIfMissing(UPLOAD_FILE_PATH, File.separator) + rawFileName + "_" +
                DateUtil.current() + "." + fileType;
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return 文件大小字符串
     */
    public static String getFileSize(MultipartFile file) {
        final long kb = 1024;
        final long mb = kb * 1024;
        long fileSize = file.getSize();
        if (fileSize >= mb) {
            return new BigDecimal(fileSize / mb).setScale(2, RoundingMode.HALF_UP).doubleValue() + "MB";
        } else if (fileSize >= kb) {
            return new BigDecimal(fileSize / kb).setScale(2, RoundingMode.HALF_UP).doubleValue() + "KB";
        } else {
            return new BigDecimal(fileSize).setScale(2, RoundingMode.HALF_UP).doubleValue() + "B";
        }
    }
}
