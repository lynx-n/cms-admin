package com.cms.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * cms_user_role_relation
 * @author 
 */
@Data
public class CmsUserRoleRelation implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;
}