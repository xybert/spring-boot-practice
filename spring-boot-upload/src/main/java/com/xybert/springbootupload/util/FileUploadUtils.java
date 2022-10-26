package com.xybert.springbootupload.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.xybert.springbootupload.enums.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author xybert
 * @description UploadFileUtils 文件上传工具类
 * @date 2022/10/26 13:55
 */

@Slf4j
@Component
public class FileUploadUtils {

    private static final String UPLOAD_FILE_PATH = "CD:\\";

    /**
     * 文件上传
     *
     * @param file 文件
     * @return boolean
     */
    public static boolean uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("文件内容为空，请选择文件");
            return false;
        }
        String filename = file.getOriginalFilename();
        String rawFileName = StrUtil.subBefore(filename, ".", true);
        String fileType = StrUtil.subAfter(filename, ".", true);
        if (!isImage(file) && !checkImageFormat(file)) {
            log.error("文件不符合系统要求，文件：{}", filename);
            return false;
        }
        // 生成新的文件名称
        String newFileName = StrUtil.appendIfMissing(UPLOAD_FILE_PATH, File.separator) + rawFileName + "_" + DateUtil.current() +
                "." + fileType;
        try {
            file.transferTo(new File(newFileName));
        } catch (IOException e) {
            log.error("文件上传失败，文件：{}，上传路径：{}", filename, UPLOAD_FILE_PATH);
            return false;
        }
        log.info("文件上传成功，文件：{}", newFileName);
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
}
