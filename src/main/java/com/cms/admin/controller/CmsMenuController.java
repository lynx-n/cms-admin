package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.MenuConstant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.MenuAuthorityEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
@Api(tags = "菜单管理", value = "菜单管理")
@Slf4j
public class CmsMenuController {

  @Resource private CmsMenuService menuService;

  @GetMapping()
  @ApiOperation("查询菜单")
  public ResponseEntity<List<MenuAuthorityEntity>> getAllMenu() {
    List<MenuAuthorityEntity> menus = menuService.selectMenus();
    return ResponseUtils.success(menus);
  }

  @PostMapping()
  @ApiOperation("插入菜单")
  @SystemLog(type = MenuConstant.MENU_TYPE, operation = MenuConstant.INSERT_MENU)
  public ResponseEntity<Integer> batchInsertMenus(@RequestBody List<MenuAuthorityEntity> menuList) {
    return ResponseUtils.success(menuService.insertMenus(menuList));
  }

  @PutMapping()
  @ApiOperation("更新菜单")
  @SystemLog(type = MenuConstant.MENU_TYPE, operation = MenuConstant.INSERT_MENU)
  public ResponseEntity<Integer> updateMenus(@RequestBody List<MenuAuthorityEntity> menuList) {
    log.info("enter update menus:{}", menuList);
    return ResponseUtils.success(menuService.updateMenus(menuList));
  }

  @DeleteMapping("/{id}")
  @ApiOperation("删除菜单")
  @SystemLog(type = MenuConstant.MENU_TYPE, operation = MenuConstant.DELETE_MENU)
  public ResponseEntity<Integer> deleteMenus(@PathVariable("id") Integer id) {
    log.info("enter delete menu:{}", id);
    return ResponseUtils.success(menuService.deleteMenuByIds(Collections.singletonList(id)));
  }
}
