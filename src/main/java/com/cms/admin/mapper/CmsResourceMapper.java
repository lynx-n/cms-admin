package com.cms.admin.mapper;

import com.cms.admin.entity.ResourceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsResourceMapper {
    int deleteResourceById(Integer id);

    int insertCmsResource(ResourceEntity resource);

    int insertSelective(ResourceEntity record);

    ResourceEntity selectResourceById(Integer id);

    ResourceEntity selectResourceByMD5(String md5);

    ResourceEntity selectResourceByUuid(String uuid);

    List<ResourceEntity> selectResources();

    int updateCmsResourceSelective(ResourceEntity record);

    int updateCmsResource(ResourceEntity record);
}