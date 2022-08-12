package com.cms.admin.entity;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class SmallFileInfo {
    /**
     * 文件objectId
     */
    String id;
    /**
     * 文件id
     */
    String fileId;
    /**
     * 文件名称
     */
    String fileName;
    /**
     * 文件实体
     */
    Binary content;
    /**
     * 文件类型
     */
    String contentType;
    /**
     * 上传者
     */
    String userAccount;
    /**
     * 更新时间
     */
    String updateTime;

}
