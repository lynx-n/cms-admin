package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.DeviceConstant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.DeviceEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.request.DeviceSearchRequest;
import com.cms.admin.service.CmsDeviceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/device")
@Api(value = "设备管理", tags = "设备管理")
@Slf4j
public class CmsDeviceController {
  @Resource private CmsDeviceService deviceService;

  @ApiOperation("设备列表-分页查询")
  @GetMapping("/{page}/{size}")
  @SystemLog(type = DeviceConstant.DEVICE_TYPE, operation = DeviceConstant.QUERY_ALL_DEVICE)
  public ResponseEntity<PageInfo<DeviceEntity>> getAllDevice(
      @PathVariable(value = "page") Integer page, @PathVariable("size") Integer size) {
    log.info("query all device, pageNumber:{} pageSize:{}", page, size);
    PageInfo<DeviceEntity> cmsDevicePageInfo = deviceService.selectDevices(page, size);
    return ResponseUtils.success(cmsDevicePageInfo);
  }

  @ApiOperation("添加设备")
  @PostMapping("")
  public ResponseEntity<Object> addDevice(@RequestBody DeviceEntity device) {
    DeviceEntity device1 = deviceService.addDevice(device);
    return ResponseUtils.success(device1);
  }

  @ApiOperation("设备查询-多条件查询")
  @GetMapping()
  public ResponseEntity<Object> searchDevice(@Validated DeviceSearchRequest request) {
    log.info("device request params:{}", request);
    PageInfo<DeviceEntity> devices = deviceService.searchDevices(request);
    return ResponseUtils.success(devices);
  }
}
