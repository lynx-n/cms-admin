package com.cms.admin.handler.impl;

import com.cms.admin.entity.ResourceEntity;
import com.cms.admin.handler.AbstractHandler;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author greatli @ClassName VideoHandler.java @Description 视频文件文件处理
 * @createTime 2021年12月25日 21:23:00
 */
@Component
public class VideoHandler extends AbstractHandler {
  @Override
  public boolean checkFile(File file) {
    return false;
  }

  @Override
  public ResourceEntity dealResource(File file, String contentType) {
    // 1-封装resource的基本信息
    ResourceEntity resource = new ResourceEntity();
    return resource;
  }
}
