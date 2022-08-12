package com.cms.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * cms_menu_authority
 * @author 
 */
@Data
public class CmsMenuAuthorityEntity implements Serializable {
    private Integer id;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 菜单描述
     */
    private String menuDescription;

    /**
     * 组件列表
     */
    private String component;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 元信息
     */
    private String meta;

    List<CmsMenuAuthorityEntity> children;

    private static final long serialVersionUID = 1L;
}