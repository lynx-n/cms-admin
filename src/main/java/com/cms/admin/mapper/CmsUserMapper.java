package com.cms.admin.mapper;

import com.cms.admin.entity.UserEntity;
import com.cms.admin.vo.CmsUserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmsUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertCmsUser(UserEntity userEntity);

    int insertSelective(UserEntity userEntity);

    UserEntity selectCmsUserByUsername(String username);

    CmsUserVO selectCmsUserVOById(Integer id);

    int updateByPrimaryKeySelective(UserEntity userEntity);

    int updateByPrimaryKey(UserEntity userEntity);
}