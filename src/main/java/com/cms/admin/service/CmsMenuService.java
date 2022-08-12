package com.cms.admin.service;

import com.cms.admin.entity.CmsMenuAuthorityEntity;

import java.util.List;

public interface CmsMenuService {
    List<CmsMenuAuthorityEntity> selectMenus();
}
