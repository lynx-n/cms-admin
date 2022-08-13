package com.cms.admin.mapper;

import com.cms.admin.entity.CmsLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsLogMapper {
  /**
   * 插入一条日志
   * @param record 日志
   * @return 成功个数
   */
  int insert(CmsLogEntity record);

  /**
   * 查询基本日志
   * @return 日志列表
   */
  List<CmsLogEntity> selectBaseLog();
}
