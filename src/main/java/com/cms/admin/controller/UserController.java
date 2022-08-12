package com.cms.admin.controller;

import com.cms.admin.common.AesUtils;
import com.cms.admin.common.Constant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.common.UUIDUtils;
import com.cms.admin.entity.CmsRole;
import com.cms.admin.entity.CmsUser;
import com.cms.admin.entity.CmsUserRoleRelation;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.params.UserAddParams;
import com.cms.admin.service.CmsRoleService;
import com.cms.admin.service.CmsUserRoleRelationService;
import com.cms.admin.service.CmsUserService;
import com.cms.admin.vo.CmsUserVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/user")
@Api("用户管理")
@Slf4j
public class UserController {

    @Resource
    private CmsUserService userService;

    @Resource
    private CmsRoleService roleService;

    @Resource
    private CmsUserRoleRelationService userRoleRelationService;

    @PostMapping("")
    public ResponseEntity<Object> register(@RequestBody UserAddParams userAddParams) {
        // 非空校验
        if (Strings.isBlank(userAddParams.getUsername()) || Strings.isBlank(userAddParams.getPassword())) {
            return ResponseUtils.badRequest(Constant.PARAMS_ERROR.getValue());
        }
        // 长度校验
        if (userAddParams.getUsername().length() >= 20 || userAddParams.getPassword().length() >= 20) {
            return ResponseUtils.badRequest(Constant.PARAMS_LEN_ERROR.getValue());
        }
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
        CmsUser cmsUser = new CmsUser();
        String encryptKey = UUIDUtils.getEncryptKey();
        cmsUser.setUserName(userAddParams.getUsername());
        cmsUser.setPassword(AesUtils.encrypt(userAddParams.getPassword(), encryptKey));
        cmsUser.setEncryptKey(encryptKey);
        cmsUser.setCreatedBy(userAddParams.getUsername());
        cmsUser.setUpdatedBy(userAddParams.getUsername());
        // 插入用户信息
        int res = userService.insertCmsUser(cmsUser);

        // 插入用户-角色信息
        CmsUserRoleRelation userRoleRelation = new CmsUserRoleRelation();
        userRoleRelation.setUserId(cmsUser.getId());
        userRoleRelation.setRoleId(roleId);
        userRoleRelationService.insertUserRoleRelation(userRoleRelation);

        // 返回结果
        CmsUserVO cmsUserVO = userService.selectCmsUserVOById(cmsUser.getId());

        return ResponseUtils.success(cmsUserVO);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(UserAddParams userAddParams) {
        // 非空校验
        if (Strings.isBlank(userAddParams.getUsername()) || Strings.isBlank(userAddParams.getPassword())) {
            return ResponseUtils.badRequest(Constant.USER_USERNAME_PASSWORD_ISNULL_CN.getValue());
        }
        String username = userAddParams.getUsername();
        CmsUser cmsUser = userService.selectCmsUserByUsername(username);
        String decryptPassword = "";
        if (cmsUser != null) {
            decryptPassword = AesUtils.decrypt(cmsUser.getPassword(), cmsUser.getEncryptKey());
        }
        if (cmsUser == null || !userAddParams.getPassword().equals(decryptPassword)) {
            return ResponseUtils.badRequest(Constant.USER_USERNAME_PASSWORD_IS_ERROR_CN.getValue());
        }

        return ResponseUtils.success(cmsUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId){
        CmsUserVO cmsUserVO = userService.selectCmsUserVOById(userId);
        return ResponseUtils.success(cmsUserVO);
    }


}
