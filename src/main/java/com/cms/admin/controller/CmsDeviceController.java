package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.DeviceConstant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.common.UUIDUtils;
import com.cms.admin.entity.CmsDevice;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsDeviceService;
import com.github.pagehelper.PageInfo;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
  public ResponseEntity<PageInfo<CmsDevice>> getAllDevice(
      @PathVariable(value = "page") Integer page, @PathVariable("size") Integer size) {
    log.info("query all device, pageNumber:{} pageSize:{}", page, size);
    PageInfo<CmsDevice> cmsDevicePageInfo = deviceService.selectDevices(page, size);
    return ResponseUtils.success(cmsDevicePageInfo);
  }

  @ApiOperation("添加设备")
  @PostMapping("")
  public ResponseEntity<Object> addDevice(@RequestBody CmsDevice device) {
    String ip = device.getDeviceIp();
    String mac = device.getDeviceMac();
    String position = device.getDevicePosition();
    device.setCreatedBy("liyang");
    device.setUpdatedBy("liyang");
    // 校验
    device.setDeviceNumber(UUIDUtils.getDeviceId());
    CmsDevice device1 = deviceService.addDevice(device);
    return ResponseUtils.success(device1);
  }

  @ApiOperation("通过设备id查询-分页查询")
  @GetMapping("{id}/{page}/{size}")
  public ResponseEntity<Object> selectDeviceById(
      @PathVariable("id") Integer id,
      @PathVariable("page") Integer page,
      @PathVariable("size") Integer size) {
    PageInfo<CmsDevice> devices = deviceService.selectDevicesById(id, page, size);
    return ResponseUtils.success(devices);
  }

  @ApiOperation("通过ip查询-分页查询")
  @GetMapping("{ip}/{page}/{size}")
  public ResponseEntity<Object> selectDeviceByIp(
      @PathVariable("ip") String ip,
      @PathVariable("page") Integer page,
      @PathVariable("size") Integer size) {
    PageInfo<CmsDevice> devices = deviceService.selectDevicesByIp(ip, page, size);
    return ResponseUtils.success(devices);
  }

  @ApiOperation("通过mac查询-分页查询")
  @GetMapping("{mac}/{page}/{size}")
  public ResponseEntity<Object> selectDeviceByMac(
      @PathVariable("mac") String mac,
      @PathVariable("page") Integer page,
      @PathVariable("size") Integer size) {
    PageInfo<CmsDevice> devices = deviceService.selectDevicesByIp(mac, page, size);
    return ResponseUtils.success(devices);
  }
}
