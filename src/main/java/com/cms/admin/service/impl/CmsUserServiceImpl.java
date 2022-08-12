package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsUser;
import com.cms.admin.mapper.CmsUserMapper;
import com.cms.admin.service.CmsUserService;
import com.cms.admin.vo.CmsUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CmsUserServiceImpl implements CmsUserService {

    @Resource
    private CmsUserMapper userMapper;

    @Override
    public CmsUser selectCmsUserByUsername(String username) {

        return userMapper.selectCmsUserByUsername(username);
    }

    @Override
    public int insertCmsUser(CmsUser cmsUser) {
        log.info("ENTER INSERT CMS USER SERVICE");
        return userMapper.insertCmsUser(cmsUser);
    }

    @Override
    public CmsUserVO selectCmsUserVOById(Integer id) {
        return userMapper.selectCmsUserVOById(id);
    }
}
