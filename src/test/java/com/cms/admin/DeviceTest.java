package com.cms.admin;

import com.cms.admin.controller.CmsDeviceController;
import com.cms.admin.mapper.CmsDeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ：GreatLi
 * @date ：Created 2022/8/13 18:23
 * @description：设备页面测试
 */
@Slf4j
@SpringBootTest
public class DeviceTest {

  @Resource private CmsDeviceController deviceController;

  @Test
  public void deviceQueryTest() {
    deviceController.getAllDevice(0, 10);
  }
}
