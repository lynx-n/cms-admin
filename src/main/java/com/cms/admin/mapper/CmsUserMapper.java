package com.cms.admin.mapper;

import com.cms.admin.entity.CmsUser;
import com.cms.admin.vo.CmsUserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmsUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertCmsUser(CmsUser cmsUser);

    int insertSelective(CmsUser cmsUser);

    CmsUser selectCmsUserByUsername(String username);

    CmsUserVO selectCmsUserVOById(Integer id);

    int updateByPrimaryKeySelective(CmsUser cmsUser);

    int updateByPrimaryKey(CmsUser cmsUser);
}