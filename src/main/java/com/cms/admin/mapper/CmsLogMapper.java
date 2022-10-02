package com.cms.admin.mapper;

import com.cms.admin.entity.CmsLogEntity;
import com.cms.admin.request.LogSearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsLogMapper {
  /**
   * 插入一条日志
   *
   * @param record 日志
   * @return 成功个数
   */
  int insert(CmsLogEntity record);

  /**
   * 查询基本日志
   *
   * @return 日志列表
   */
  List<CmsLogEntity> selectBaseLog(LogSearchRequest request);

  /**
   * 删除多少天前的日志
   *
   * @param day 时间
   * @return 删除成功个数
   */
  int deleteLogByDay(@Param("day") int day);
}
