package com.cms.admin;

import com.cms.admin.service.CmsLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ：GreatLi
 * @date ：Created 2022/8/13 22:45
 * @description：日志测试
 */
@Slf4j
@SpringBootTest
public class CmsLogServiceTest {

  @Resource private CmsLogService logService;

}
