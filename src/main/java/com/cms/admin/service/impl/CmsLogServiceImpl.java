package com.cms.admin.service.impl;

import com.cms.admin.entity.CmsDevice;
import com.cms.admin.entity.CmsLogEntity;
import com.cms.admin.mapper.CmsLogMapper;
import com.cms.admin.request.LogSearchRequest;
import com.cms.admin.service.CmsLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：GreatLi
 * @date ：Created 2022/8/13 22:15
 * @description：日志service
 */
@Service
@Slf4j
public class CmsLogServiceImpl implements CmsLogService {
  @Resource private CmsLogMapper logMapper;

  @Override
  public int insert(CmsLogEntity record) {
    log.info("insert sysLog,type:{}", record.getOperationType());
    return logMapper.insert(record);
  }

  @Override
  public PageInfo<CmsLogEntity> searchBaseLog(LogSearchRequest request) {
    PageHelper.startPage(request.getPageNo(), request.getPageSize());
    List<CmsLogEntity> baseLog = logMapper.selectBaseLog(request);
    return new PageInfo<>(baseLog);
  }

  @Override
  public int deleteLogByDay(int day) {
    return logMapper.deleteLogByDay(day);
  }
}
