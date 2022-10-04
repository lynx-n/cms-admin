package com.cms.admin.mapper;

import com.cms.admin.entity.UserRoleRelation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmsUserRoleRelationMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户角色关联
     * @param userRoleRelation
     * @return
     */
    int insertUserRoleRelation(UserRoleRelation userRoleRelation);

    int insertSelective(UserRoleRelation record);

    UserRoleRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}