package com.cms.admin.service.impl;

import com.cms.admin.entity.RoleEntity;
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
    public RoleEntity selectCmsRoleById(Integer id) {
        return roleMapper.selectCmsRoleById(id);
    }

    @Override
    public List<RoleEntity> selectCmsRoles() {
        return roleMapper.selectCmsRoles();
    }

    @Override
    public int updateCmsRole(RoleEntity role) {
        log.info("UPDATE CMS ROLE :{}", role.toString());
        return roleMapper.updateCmsRole(role);
    }

    @Override
    public int deleteCmsRoleById(Integer id) {
        return roleMapper.deleteCmsRoleById(id);
    }

    @Override
    public RoleEntity insertCmsRole(RoleEntity role) {
        roleMapper.insertCmsRole(role);
        return role;
    }


}
