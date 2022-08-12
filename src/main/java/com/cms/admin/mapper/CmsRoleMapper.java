package com.cms.admin.mapper;

import com.cms.admin.entity.CmsRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsRoleMapper {
    int deleteCmsRoleById(Integer id);

    int insertCmsRole(CmsRole record);

    int insertSelective(CmsRole record);

    CmsRole selectCmsRoleById(Integer id);

    List<CmsRole> selectCmsRoles();

    int updateCmsRole(CmsRole record);

    int updateByPrimaryKey(CmsRole record);
}