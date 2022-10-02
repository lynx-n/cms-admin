package com.cms.admin;

import com.cms.admin.controller.CmsLogController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ：GreatLi
 * @date ：Created 2022/8/14 14:08
 * @description：日志测试
 */
@SpringBootTest
public class CmsLogTest {

  @Resource private CmsLogController cmsLogController;

  @Test
  public void deleteLogTest() {
    cmsLogController.deleteLogByDay(2);
  }
}
