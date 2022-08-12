package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsResource;
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
    public int insertCmsResource(CmsResource resource) {
        return resourceMapper.insertCmsResource(resource);
    }

    @Override
    public CmsResource selectResourceByMD5(String md5) {
        return resourceMapper.selectResourceByMD5(md5);
    }

    @Override
    public CmsResource selectResourceByUuid(String uuid) {
        return resourceMapper.selectResourceByUuid(uuid);
    }

    @Override
    public PageInfo<CmsResource> selectResourcePage(int page, int size) {
        PageHelper.startPage(page, size);
        List<CmsResource> resources = resourceMapper.selectResources();
        PageInfo<CmsResource> pageInfo = new PageInfo<>(resources);
        return pageInfo;
    }


}
