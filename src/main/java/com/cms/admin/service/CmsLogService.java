package com.cms.admin.service;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.entity.CmsLogEntity;
import com.cms.admin.request.LogSearchRequest;
import com.github.pagehelper.PageInfo;

public interface CmsLogService {
  /**
   * 插入log日志
   *
   * @param record 日志记录
   * @return 成功个数
   */
  int insert(CmsLogEntity record);

  /**
   * @param request 日志请求
   * @return 日志信息
   */
  PageInfo<CmsLogEntity> searchBaseLog(LogSearchRequest request);

  /**
   * 删除多少天前的日志
   *
   * @param day 时间
   * @return 删除个数
   */
  int deleteLogByDay(int day);
}
