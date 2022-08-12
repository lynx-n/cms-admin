package com.cms.admin.controller;

import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.CmsMenuAuthorityEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsMenuService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@Api("菜单管理")
public class CmsMenuController {

    @Resource
    private CmsMenuService menuService;

    @GetMapping()
    public ResponseEntity<Object> getAllMenu() {
        List<CmsMenuAuthorityEntity> menus = menuService.selectMenus();
        return ResponseUtils.success(menus);
    }
}
