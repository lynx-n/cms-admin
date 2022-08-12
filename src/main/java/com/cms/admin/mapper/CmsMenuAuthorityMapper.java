package com.cms.admin.mapper;

import com.cms.admin.entity.CmsMenuAuthorityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsMenuAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsMenuAuthorityEntity record);

    int insertSelective(CmsMenuAuthorityEntity record);

    CmsMenuAuthorityEntity selectByPrimaryKey(Integer id);

    List<CmsMenuAuthorityEntity> selectMenus();

    int updateByPrimaryKeySelective(CmsMenuAuthorityEntity record);

    int updateByPrimaryKey(CmsMenuAuthorityEntity record);
}