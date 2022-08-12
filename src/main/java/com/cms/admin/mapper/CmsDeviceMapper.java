package com.cms.admin.mapper;

import com.cms.admin.entity.CmsDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsDevice record);

    int insertSelective(CmsDevice record);

    List<CmsDevice> selectDeviceById(Integer id);

    List<CmsDevice> selectDeviceByIp(String ip);

    List<CmsDevice> selectDeviceByMac(String mac);

    List<CmsDevice> selectDevices();

    int updateByPrimaryKeySelective(CmsDevice record);

    int updateByPrimaryKey(CmsDevice record);
}