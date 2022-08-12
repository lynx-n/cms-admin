package com.cms.admin.entity;

import lombok.Data;

import java.io.File;

/**
 * 媒体文件的基本信息
 */
@Data
public class MediaBaseInfoEntity {
    /**
     * 宽
     */
    Integer width;
    /**
     * 高
     */
    Integer height;
    /**
     * 文件地址
     */
    File file;
}
