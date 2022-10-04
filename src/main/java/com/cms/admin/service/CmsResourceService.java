package com.cms.admin.service;


import com.cms.admin.entity.ResourceEntity;
import com.github.pagehelper.PageInfo;

public interface CmsResourceService {

    /**
     * 插入一条资源
     *
     * @param resource
     * @return
     */
    int insertCmsResource(ResourceEntity resource);


    /**
     * 根据MD5取出一条最新的数据
     *
     * @param md5
     * @return
     */
    ResourceEntity selectResourceByMD5(String md5);

    /**
     * 根据uuid 获取资源
     *
     * @param uuid
     * @return
     */
    ResourceEntity selectResourceByUuid(String uuid);


    PageInfo<ResourceEntity> selectResourcePage(int page, int size);


}
