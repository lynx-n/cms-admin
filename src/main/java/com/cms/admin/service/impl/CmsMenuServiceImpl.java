package com.cms.admin.service.impl;

import com.cms.admin.entity.MenuAuthorityEntity;
import com.cms.admin.mapper.CmsMenuAuthorityMapper;
import com.cms.admin.service.CmsMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CmsMenuServiceImpl implements CmsMenuService {

  @Resource private CmsMenuAuthorityMapper menuAuthorityMapper;

  @Override
  public List<MenuAuthorityEntity> selectMenus() {
    return menuAuthorityMapper.selectMenus();
  }

  @Override
  public int insertMenus(List<MenuAuthorityEntity> entityList) {
    return menuAuthorityMapper.insertMenus(entityList);
  }

  @Override
  public int updateMenus(List<MenuAuthorityEntity> entityList) {
    return menuAuthorityMapper.updateMenus(entityList);
  }

  @Override
  public int deleteMenuByIds(List<Integer> ids) {
    return menuAuthorityMapper.deleteMenuByIds(ids);
  }
}
