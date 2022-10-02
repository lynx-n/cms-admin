package com.cms.admin.mapper;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.request.DeviceSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsDeviceMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(CmsDevice record);

  int insertSelective(CmsDevice record);

  List<CmsDevice> searchDevices(DeviceSearchRequest request);

  List<CmsDevice> selectDevices();

  int updateByPrimaryKeySelective(CmsDevice record);

  int updateByPrimaryKey(CmsDevice record);
}
