package com.cms.admin.service;

import com.cms.admin.entity.DeviceEntity;
import com.cms.admin.request.DeviceSearchRequest;
import com.github.pagehelper.PageInfo;

public interface CmsDeviceService {
    PageInfo<DeviceEntity> selectDevices(int page, int size);

    DeviceEntity addDevice(DeviceEntity device);

    PageInfo<DeviceEntity>  searchDevices(DeviceSearchRequest request);
}
