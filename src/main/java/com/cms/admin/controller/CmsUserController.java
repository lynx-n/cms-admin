package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.*;
import com.cms.admin.entity.RoleEntity;
import com.cms.admin.entity.UserEntity;
import com.cms.admin.entity.UserRoleRelation;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.params.UserAddParams;
import com.cms.admin.params.UserLoginParams;
import com.cms.admin.service.CmsRoleService;
import com.cms.admin.service.CmsUserRoleRelationService;
import com.cms.admin.service.CmsUserService;
import com.cms.admin.vo.CmsUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/user")
@Api(value = "用户管理", tags = "用户管理")
@Slf4j
public class CmsUserController {

  @Resource private CmsUserService userService;

  @Resource private CmsRoleService roleService;

  @Resource private CmsUserRoleRelationService userRoleRelationService;

  @PostMapping("")
  @ApiOperation("注册用户")
  @SystemLog(type = UserConstant.USER_TYPE, operation = UserConstant.USER_REGISTER)
  public ResponseEntity<Object> register(@RequestBody @Validated UserAddParams userAddParams) {

    log.info("register user:{}", userAddParams.getUsername());
    // 重复名校验
    if (userService.selectCmsUserByUsername(userAddParams.getUsername()) != null) {
      return ResponseUtils.forbidden(Constant.USER_REGISTERED_CN.getValue());
    }
    // 是否是错误角色id校验
    int roleId = userAddParams.getRoleId();
    RoleEntity roleEntity = roleService.selectCmsRoleById(roleId);
    if (roleEntity == null) {
      return ResponseUtils.badRequest(Constant.PARAMS_LEN_ERROR.getValue());
    }

    // 创建用户
    String encryptKey = UUIDUtils.getEncryptKey();
    UserEntity userEntity =
        UserEntity.builder()
            .encryptKey(encryptKey)
            .userName(userAddParams.getUsername())
            .password(AesUtils.encrypt(userAddParams.getPassword(), encryptKey))
            .createdBy(userAddParams.getUsername())
            .updatedBy(userAddParams.getPassword())
            .build();

    // 插入用户信息
    userService.insertCmsUser(userEntity);

    // 插入用户-角色信息
    UserRoleRelation userRoleRelation =
        UserRoleRelation.builder().userId(userEntity.getId()).roleId(roleId).build();
    userRoleRelationService.insertUserRoleRelation(userRoleRelation);

    // 返回结果
    CmsUserVO cmsUserVO = userService.selectCmsUserById(userEntity.getId());

    return ResponseUtils.success(cmsUserVO);
  }

  @PostMapping("/login")
  @ApiOperation("用户登录")
  @SystemLog(type = UserConstant.USER_TYPE, operation = UserConstant.USER_LOGIN)
  public ResponseEntity<Object> login(UserLoginParams userLoginParams) {
    log.info("user [{}] login", userLoginParams.getUsername());
    String username = userLoginParams.getUsername();
    UserEntity userEntity = userService.selectCmsUserByUsername(username);
    String decryptPassword = "";
    if (userEntity != null) {
      decryptPassword = AesUtils.decrypt(userEntity.getPassword(), userEntity.getEncryptKey());
    }
    if (userEntity == null || !userLoginParams.getPassword().equals(decryptPassword)) {
      log.info("password error");
      return ResponseUtils.badRequest(Constant.USER_USERNAME_PASSWORD_IS_ERROR_CN.getValue());
    }

    return ResponseUtils.success(userEntity);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId) {
    log.info("query user info by user id:{}", userId);
    CmsUserVO cmsUserVO = userService.selectCmsUserById(userId);
    return ResponseUtils.success(cmsUserVO);
  }
}
