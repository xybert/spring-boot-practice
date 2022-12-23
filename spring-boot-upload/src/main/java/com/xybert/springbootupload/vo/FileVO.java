package com.xybert.springbootupload.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author xybert
 * @description 文件信息
 * @date 2022/10/30 16:03
 */

@Data
public class FileVO {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件拓展名
     */
    private String extension;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 上传时间
     */
    private Date time;
}
