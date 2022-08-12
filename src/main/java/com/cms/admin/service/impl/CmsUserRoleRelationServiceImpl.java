package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsUserRoleRelation;
import com.cms.admin.mapper.CmsUserRoleRelationMapper;
import com.cms.admin.service.CmsUserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CmsUserRoleRelationServiceImpl implements CmsUserRoleRelationService {
    @Resource
    private CmsUserRoleRelationMapper userRoleRelationMapper;

    @Override
    public int insertUserRoleRelation(CmsUserRoleRelation userRoleRelation) {
        log.info("INSERT USER ROLE RELATION SERVICE [{}]", userRoleRelation.toString());
        return userRoleRelationMapper.insertUserRoleRelation(userRoleRelation);
    }
}
