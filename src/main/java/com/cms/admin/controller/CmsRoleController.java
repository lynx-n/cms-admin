package com.cms.admin.controller;

import com.cms.admin.common.Constant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.CmsRole;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/v1/role")
@Api("资源管理")
public class CmsRoleController {

    @Resource
    private CmsRoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<CmsRole>> getRoles() {
        List<CmsRole> cmsRoles = roleService.selectCmsRoles();
        return ResponseUtils.success(cmsRoles);
    }

    @PutMapping
    public ResponseEntity<String> updateRole(@RequestBody CmsRole cmsRole) {
        roleService.updateCmsRole(cmsRole);
        return ResponseUtils.success(Constant.RESPONSE_SUCCESS.getValue());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCmsRoleById(@PathVariable(value = "id") Integer id) {
        roleService.deleteCmsRoleById(id);
        return ResponseUtils.success(Constant.RESPONSE_SUCCESS.getValue());
    }

    @PostMapping()
    public ResponseEntity<CmsRole> insertCmsRole(@RequestBody CmsRole role){
        CmsRole cmsRole = roleService.insertCmsRole(role);
        return ResponseUtils.success(cmsRole);
    }
}
