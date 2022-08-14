package com.cms.admin.controller;

import com.cms.admin.aspect.SystemLog;
import com.cms.admin.common.LogConstant;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.CmsLogEntity;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.service.CmsLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/log")
@Api(value = "日志管理", tags = "日志管理")
@Slf4j
@Validated
public class CmsLogController {
  @Resource private CmsLogService logService;

  @ApiOperation("日志信息-分页查询")
  @GetMapping("/{page}/{size}")
  @SystemLog(type = LogConstant.LOG_TYPE, operation = LogConstant.QUERY_ALL_LOG)
  public ResponseEntity<PageInfo<CmsLogEntity>> listLogs(
      @PathVariable(value = "page") Integer page, @PathVariable("size") Integer size) {
    log.info("query all logs, pageNumber:{} pageSize:{}", page, size);
    PageInfo<CmsLogEntity> logInfo = logService.listBaseLog(page, size);
    return ResponseUtils.success(logInfo);
  }

  @ApiOperation("日志删除")
  @DeleteMapping("/{day}")
  @SystemLog(type = LogConstant.LOG_TYPE, operation = LogConstant.DELETE_LOG)
  public ResponseEntity<Integer> deleteLogByDay(@PathVariable("day") @Min(value = 0) int day) {
    log.info("delete log before {} days", day);
    int logCount = logService.deleteLogByDay(day);
    log.info("delete success,count:{}", logCount);
    return ResponseUtils.success(logCount);
  }
}
