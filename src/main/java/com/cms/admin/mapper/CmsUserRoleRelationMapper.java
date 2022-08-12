package com.cms.admin.mapper;

import com.cms.admin.entity.CmsUserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmsUserRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户角色关联
     * @param userRoleRelation
     * @return
     */
    int insertUserRoleRelation(CmsUserRoleRelation userRoleRelation);

    int insertSelective(CmsUserRoleRelation record);

    CmsUserRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsUserRoleRelation record);

    int updateByPrimaryKey(CmsUserRoleRelation record);
}