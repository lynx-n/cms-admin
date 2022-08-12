package com.cms.admin.service;


import com.cms.admin.entity.CmsResource;
import com.github.pagehelper.PageInfo;

public interface CmsResourceService {

    /**
     * 插入一条资源
     *
     * @param resource
     * @return
     */
    int insertCmsResource(CmsResource resource);


    /**
     * 根据MD5取出一条最新的数据
     *
     * @param md5
     * @return
     */
    CmsResource selectResourceByMD5(String md5);

    /**
     * 根据uuid 获取资源
     *
     * @param uuid
     * @return
     */
    CmsResource selectResourceByUuid(String uuid);


    PageInfo<CmsResource> selectResourcePage(int page, int size);


}
