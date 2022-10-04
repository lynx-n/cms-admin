package com.cms.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cms_resource
 * @author 
 */
@Data
public class ResourceEntity implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * uui 资源唯一标识
     */
    private String uuid;

    /**
     * 存入mongodb的文件id
     */
    private String fileId;

    /**
     * 文件的中文名称
     */
    private String nameCn;

    /**
     * 文件的md5值，防止重复上传
     */
    private String md5;

    /**
     * 资源的缩略图
     */
    private String icon;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件类型 1-图片 2-视频 3-文本
     */
    private String fileType;

    /**
     * 文件实体下载路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 请求文件类型
     */
    private String contentType;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 更新者
     */
    private String updatedBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 资源宽度
     */
    private Integer width;

    /**
     * 资源高度
     */
    private Integer height;


    private static final long serialVersionUID = 1L;
}