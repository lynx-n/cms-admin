package com.cms.admin.service;

import com.cms.admin.entity.MenuAuthorityEntity;
import io.swagger.models.auth.In;

import java.util.List;

public interface CmsMenuService {
  List<MenuAuthorityEntity> selectMenus();

  int insertMenus(List<MenuAuthorityEntity> entityList);

  int updateMenus(List<MenuAuthorityEntity> entityList);

  int deleteMenuByIds(List<Integer> ids);
}
