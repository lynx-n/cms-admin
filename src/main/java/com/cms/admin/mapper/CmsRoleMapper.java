package com.cms.admin.mapper;

import com.cms.admin.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsRoleMapper {
    int deleteCmsRoleById(Integer id);

    int insertCmsRole(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectCmsRoleById(Integer id);

    List<RoleEntity> selectCmsRoles();

    int updateCmsRole(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);
}