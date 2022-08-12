package com.cms.admin.service;

import com.cms.admin.entity.CmsRole;

import java.util.List;

public interface CmsRoleService {
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    CmsRole selectCmsRoleById(Integer id);


    /**
     * 得到所以角色
     * @return
     */
    List<CmsRole> selectCmsRoles();

    /**
     * 更新角色信息
     * @return
     */
    int updateCmsRole(CmsRole role);

    /**
     * 通过id删除角色
     * @param id
     * @return
     */
    int deleteCmsRoleById(Integer id);

    /**
     * 插入一条角色信息
     * @param role
     * @return
     */
    CmsRole insertCmsRole(CmsRole role);

}
