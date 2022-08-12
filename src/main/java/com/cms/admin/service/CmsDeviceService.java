package com.cms.admin.service;

import com.cms.admin.entity.CmsDevice;
import com.github.pagehelper.PageInfo;

public interface CmsDeviceService {
    PageInfo<CmsDevice> selectDevices(int page, int size);

    CmsDevice addDevice(CmsDevice device);

    PageInfo<CmsDevice>  selectDevicesById(Integer id,int page, int size);

    PageInfo<CmsDevice>  selectDevicesByIp(String ip,int page, int size);

    PageInfo<CmsDevice>  selectDevicesByMac(String mac,int page, int size);
}
