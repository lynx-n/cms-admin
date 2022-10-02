package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsDevice;
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
  public PageInfo<CmsDevice> selectDevices(int page, int size) {
    PageHelper.startPage(page, size);
    List<CmsDevice> devices = deviceMapper.selectDevices();
    return new PageInfo<>(devices);
  }

  @Override
  public CmsDevice addDevice(CmsDevice device) {
    deviceMapper.insert(device);
    return device;
  }

  @Override
  public PageInfo<CmsDevice> searchDevices(DeviceSearchRequest request) {
    PageHelper.startPage(request.getPageNo(), request.getPageSize());
    List<CmsDevice> cmsDevices = deviceMapper.searchDevices(request);
    return new PageInfo<>(cmsDevices);
  }
}
