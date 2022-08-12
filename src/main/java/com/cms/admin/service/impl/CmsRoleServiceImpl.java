package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsRole;
import com.cms.admin.mapper.CmsRoleMapper;
import com.cms.admin.service.CmsRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CmsRoleServiceImpl implements CmsRoleService {
    @Resource
    private CmsRoleMapper roleMapper;


    @Override
    public CmsRole selectCmsRoleById(Integer id) {
        return roleMapper.selectCmsRoleById(id);
    }

    @Override
    public List<CmsRole> selectCmsRoles() {
        return roleMapper.selectCmsRoles();
    }

    @Override
    public int updateCmsRole(CmsRole role) {
        log.info("UPDATE CMS ROLE :{}", role.toString());
        return roleMapper.updateCmsRole(role);
    }

    @Override
    public int deleteCmsRoleById(Integer id) {
        return roleMapper.deleteCmsRoleById(id);
    }

    @Override
    public CmsRole insertCmsRole(CmsRole role) {
        roleMapper.insertCmsRole(role);
        return role;
    }


}
