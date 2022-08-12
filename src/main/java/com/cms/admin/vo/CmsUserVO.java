package com.cms.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户的详细信息
 */
@Data
public class CmsUserVO {
    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 年纪
     */
    private Byte age;

    /**
     * 手机号
     */
    private String phone;

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
     * 角色id
     */
    private Integer roleId;

    /**
     * 内容
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户头像
     */
    private String avatar;


}
