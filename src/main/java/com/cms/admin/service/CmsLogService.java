package com.cms.admin.service;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.entity.CmsLogEntity;
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
   * 查询基本日志
   *
   * @param page 页数
   * @param size 页面个数
   * @return 日志基本信息
   */
  PageInfo<CmsLogEntity> listBaseLog(int page, int size);

  /**
   * 删除多少天前的日志
   *
   * @param day 时间
   * @return 删除个数
   */
  int deleteLogByDay(int day);
}
