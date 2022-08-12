package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.mapper.CmsDeviceMapper;
import com.cms.admin.service.CmsDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsDeviceImpl implements CmsDeviceService {

    @Autowired
    private CmsDeviceMapper deviceMapper;

    @Override
    public PageInfo<CmsDevice> selectDevices(int page, int size) {
        PageHelper.startPage(page, size);
        List<CmsDevice> devices = deviceMapper.selectDevices();
        PageInfo<CmsDevice> pageInfo = new PageInfo<>(devices);
        return pageInfo;
    }

    @Override
    public CmsDevice addDevice(CmsDevice device) {
        deviceMapper.insert(device);
        return device;

    }

    @Override
    public PageInfo<CmsDevice> selectDevicesById(Integer id, int page, int size) {
        PageHelper.startPage(page, size);
        List<CmsDevice> devices = deviceMapper.selectDeviceById(id);
        PageInfo<CmsDevice> pageInfo = new PageInfo<>(devices);
        return pageInfo;
    }

    @Override
    public PageInfo<CmsDevice> selectDevicesByIp(String ip, int page, int size) {
        PageHelper.startPage(page,size);
        List<CmsDevice> devices = deviceMapper.selectDeviceByIp(ip);
        PageInfo<CmsDevice> pageInfo = new PageInfo<>(devices);
        return pageInfo;
    }

    @Override
    public PageInfo<CmsDevice> selectDevicesByMac(String mac, int page, int size) {
        PageHelper.startPage(page,size);
        List<CmsDevice> devices = deviceMapper.selectDeviceByMac(mac);
        PageInfo<CmsDevice> pageInfo = new PageInfo<>(devices);
        return pageInfo;
    }
}
