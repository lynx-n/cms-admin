package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.Constant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.common.UserConstant;
import com.cms.admin.entity.RoleEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@Api(value = "角色管理", tags = "角色管理")
public class CmsRoleController {

  @Resource private CmsRoleService roleService;

  @GetMapping("")
  @ApiOperation("全部角色查询")
  public ResponseEntity<List<RoleEntity>> getRoles() {
    List<RoleEntity> roleEntities = roleService.selectCmsRoles();
    return ResponseUtils.success(roleEntities);
  }

  @PutMapping
  @ApiOperation("更新角色")
  @SystemLog(type = UserConstant.USER_ROLE_TYPE, operation = UserConstant.USER_ROLE_UPDATE)
  public ResponseEntity<String> updateRole(@RequestBody RoleEntity roleEntity) {
    roleService.updateCmsRole(roleEntity);
    return ResponseUtils.success(Constant.RESPONSE_SUCCESS.getValue());
  }

  @DeleteMapping("/{id}")
  @ApiOperation("删除角色")
  @SystemLog(type = UserConstant.USER_ROLE_TYPE, operation = UserConstant.USER_ROlE_DELETE)
  public ResponseEntity<String> deleteCmsRoleById(@PathVariable(value = "id") Integer id) {
    roleService.deleteCmsRoleById(id);
    return ResponseUtils.success(Constant.RESPONSE_SUCCESS.getValue());
  }

  @PostMapping()
  @ApiOperation("插入角色")
  @SystemLog(type = UserConstant.USER_ROLE_TYPE, operation = UserConstant.USER_ROlE_INSERT)
  public ResponseEntity<RoleEntity> insertCmsRole(@RequestBody RoleEntity role) {
    RoleEntity roleEntity = roleService.insertCmsRole(role);
    return ResponseUtils.success(roleEntity);
  }
}
