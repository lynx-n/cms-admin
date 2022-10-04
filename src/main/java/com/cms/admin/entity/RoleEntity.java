package com.cms.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * cms_role
 * @author 
 */
@Data
public class RoleEntity implements Serializable {


    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}