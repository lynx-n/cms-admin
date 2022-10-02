package com.cms.admin.service;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.request.DeviceSearchRequest;
import com.github.pagehelper.PageInfo;

public interface CmsDeviceService {
    PageInfo<CmsDevice> selectDevices(int page, int size);

    CmsDevice addDevice(CmsDevice device);

    PageInfo<CmsDevice>  searchDevices(DeviceSearchRequest request);
}
