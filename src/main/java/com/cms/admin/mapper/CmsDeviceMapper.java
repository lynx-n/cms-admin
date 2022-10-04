package com.cms.admin.mapper;

import com.cms.admin.entity.DeviceEntity;
import com.cms.admin.request.DeviceSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsDeviceMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(DeviceEntity record);

  int insertSelective(DeviceEntity record);

  List<DeviceEntity> searchDevices(DeviceSearchRequest request);

  List<DeviceEntity> selectDevices();

  int updateByPrimaryKeySelective(DeviceEntity record);

  int updateByPrimaryKey(DeviceEntity record);
}
