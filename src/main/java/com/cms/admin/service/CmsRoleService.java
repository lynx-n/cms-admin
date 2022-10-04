package com.cms.admin.service;

import com.cms.admin.entity.RoleEntity;

import java.util.List;

public interface CmsRoleService {
  /**
   * 根据id查询角色
   *
   * @param id
   * @return
   */
  RoleEntity selectCmsRoleById(Integer id);

  /**
   * 得到所有角色
   *
   * @return 用户角色
   */
  List<RoleEntity> selectCmsRoles();

  /**
   * 更新角色信息
   *
   * @return 成功个数
   */
  int updateCmsRole(RoleEntity role);

  /**
   * 通过id删除角色
   *
   * @param id 角色id
   * @return 删除个数
   */
  int deleteCmsRoleById(Integer id);

  /**
   * 插入一条角色信息
   *
   * @param role 角色信息
   * @return 角色
   */
  RoleEntity insertCmsRole(RoleEntity role);
}
