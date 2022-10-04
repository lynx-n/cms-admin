package com.cms.admin.service.impl;

import com.cms.admin.entity.DeviceEntity;
import com.cms.admin.mapper.CmsDeviceMapper;
import com.cms.admin.request.DeviceSearchRequest;
import com.cms.admin.service.CmsDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsDeviceImpl implements CmsDeviceService {

  @Autowired private CmsDeviceMapper deviceMapper;

  @Override
  public PageInfo<DeviceEntity> selectDevices(int page, int size) {
    PageHelper.startPage(page, size);
    List<DeviceEntity> devices = deviceMapper.selectDevices();
    return new PageInfo<>(devices);
  }

  @Override
  public DeviceEntity addDevice(DeviceEntity device) {
    deviceMapper.insert(device);
    return device;
  }

  @Override
  public PageInfo<DeviceEntity> searchDevices(DeviceSearchRequest request) {
    PageHelper.startPage(request.getPageNo(), request.getPageSize());
    List<DeviceEntity> deviceEntities = deviceMapper.searchDevices(request);
    return new PageInfo<>(deviceEntities);
  }
}
