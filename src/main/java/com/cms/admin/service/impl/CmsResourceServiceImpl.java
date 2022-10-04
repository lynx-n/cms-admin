package com.cms.admin.service.impl;

import com.cms.admin.entity.ResourceEntity;
import com.cms.admin.mapper.CmsResourceMapper;
import com.cms.admin.service.CmsResourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CmsResourceServiceImpl implements CmsResourceService {

    @Resource
    CmsResourceMapper resourceMapper;

    @Override
    public int insertCmsResource(ResourceEntity resource) {
        return resourceMapper.insertCmsResource(resource);
    }

    @Override
    public ResourceEntity selectResourceByMD5(String md5) {
        return resourceMapper.selectResourceByMD5(md5);
    }

    @Override
    public ResourceEntity selectResourceByUuid(String uuid) {
        return resourceMapper.selectResourceByUuid(uuid);
    }

    @Override
    public PageInfo<ResourceEntity> selectResourcePage(int page, int size) {
        PageHelper.startPage(page, size);
        List<ResourceEntity> resources = resourceMapper.selectResources();
        PageInfo<ResourceEntity> pageInfo = new PageInfo<>(resources);
        return pageInfo;
    }


}
