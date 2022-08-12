package com.cms.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * cms_device
 *
 * @author
 */
@Data
public class CmsDevice implements Serializable {
    /**
     * 设备主键id
     */
    private Integer id;

    /**
     * 设备id
     */
    private String deviceNumber;

    /**
     * 设备ip地址
     */
    private String deviceIp;

    /**
     * 设备mac地址
     */
    private String deviceMac;

    /**
     * 设备地址信息
     */
    private String devicePosition;

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

    private static final long serialVersionUID = 1L;
}