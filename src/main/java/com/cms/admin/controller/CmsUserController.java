package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.*;
import com.cms.admin.entity.CmsRole;
import com.cms.admin.entity.CmsUser;
import com.cms.admin.entity.CmsUserRoleRelation;
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
import org.apache.logging.log4j.util.Strings;
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
    CmsRole cmsRole = roleService.selectCmsRoleById(roleId);
    if (cmsRole == null) {
      return ResponseUtils.badRequest(Constant.PARAMS_LEN_ERROR.getValue());
    }

    // 创建用户
    String encryptKey = UUIDUtils.getEncryptKey();
    CmsUser cmsUser =
        CmsUser.builder()
            .encryptKey(encryptKey)
            .userName(userAddParams.getUsername())
            .password(AesUtils.encrypt(userAddParams.getPassword(), encryptKey))
            .createdBy(userAddParams.getUsername())
            .updatedBy(userAddParams.getPassword())
            .build();

    // 插入用户信息
    userService.insertCmsUser(cmsUser);

    // 插入用户-角色信息
    CmsUserRoleRelation userRoleRelation =
        CmsUserRoleRelation.builder().userId(cmsUser.getId()).roleId(roleId).build();
    userRoleRelationService.insertUserRoleRelation(userRoleRelation);

    // 返回结果
    CmsUserVO cmsUserVO = userService.selectCmsUserById(cmsUser.getId());

    return ResponseUtils.success(cmsUserVO);
  }

  @PostMapping("/login")
  @ApiOperation("用户登录")
  @SystemLog(type = UserConstant.USER_TYPE, operation = UserConstant.USER_LOGIN)
  public ResponseEntity<Object> login(UserLoginParams userLoginParams) {
    log.info("user [{}] login", userLoginParams.getUsername());
    String username = userLoginParams.getUsername();
    CmsUser cmsUser = userService.selectCmsUserByUsername(username);
    String decryptPassword = "";
    if (cmsUser != null) {
      decryptPassword = AesUtils.decrypt(cmsUser.getPassword(), cmsUser.getEncryptKey());
    }
    if (cmsUser == null || !userLoginParams.getPassword().equals(decryptPassword)) {
      log.info("password error");
      return ResponseUtils.badRequest(Constant.USER_USERNAME_PASSWORD_IS_ERROR_CN.getValue());
    }

    return ResponseUtils.success(cmsUser);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId) {
    log.info("query user info by user id:{}", userId);
    CmsUserVO cmsUserVO = userService.selectCmsUserById(userId);
    return ResponseUtils.success(cmsUserVO);
  }
}
