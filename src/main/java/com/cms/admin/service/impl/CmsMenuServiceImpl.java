package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsMenuAuthorityEntity;
import com.cms.admin.mapper.CmsMenuAuthorityMapper;
import com.cms.admin.service.CmsMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CmsMenuServiceImpl implements CmsMenuService {

    @Resource
    private CmsMenuAuthorityMapper menuAuthorityMapper;
    @Override
    public List<CmsMenuAuthorityEntity> selectMenus() {
        return menuAuthorityMapper.selectMenus();
    }
}
