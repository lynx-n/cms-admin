package com.cms.admin.service;

import com.cms.admin.entity.CmsUserRoleRelation;

public interface CmsUserRoleRelationService {
    /**
     * 插入用户角色关联
     * @param userRoleRelation
     * @return
     */
    int insertUserRoleRelation(CmsUserRoleRelation userRoleRelation);
}
