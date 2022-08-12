package com.cms.admin.mapper;

import com.cms.admin.entity.CmsResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsResourceMapper {
    int deleteResourceById(Integer id);

    int insertCmsResource(CmsResource resource);

    int insertSelective(CmsResource record);

    CmsResource selectResourceById(Integer id);

    CmsResource selectResourceByMD5(String md5);

    CmsResource selectResourceByUuid(String uuid);

    List<CmsResource> selectResources();

    int updateCmsResourceSelective(CmsResource record);

    int updateCmsResource(CmsResource record);
}