package com.cms.admin.handler.impl;

import com.cms.admin.common.Constant;
import com.cms.admin.common.FfmpegUtils;
import com.cms.admin.common.LocalFileUtils;
import com.cms.admin.common.UUIDUtils;
import com.cms.admin.entity.ResourceEntity;
import com.cms.admin.handler.AbstractHandler;
import com.cms.admin.service.CmsResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import ws.schild.jave.info.VideoSize;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author greatli @ClassName ImageHandler.java @Description 图片文件处理
 * @createTime 2021年12月25日 21:23:00
 */
@Component
@Slf4j
public class ImageHandler extends AbstractHandler {

  @Value("${cms.admin.host}")
  private String host;

  @Resource CmsResourceService resourceService;

  @Override
  public boolean checkFile(File file) {
    return false;
  }

  @Override
  public ResourceEntity dealResource(File file, String contentType) {
    // 1-封装resource的基本信息
    ResourceEntity resource = new ResourceEntity();
    // 2-md5处理 防止文件重复上传
    try (FileInputStream inputStream = new FileInputStream(file)) {
      String md5 = DigestUtils.md5DigestAsHex(inputStream);
      //            CmsResource resourceByMD5 = resourceService.selectResourceByMD5(md5);
      //            // 复制一份数据，不再存储到mongo中
      //            if (resourceByMD5 != null) {
      //                resourceByMD5.setCreatedBy("2121");
      //                resourceByMD5.setUpdatedBy("2121");
      //                resourceByMD5.setUuid(UUIDUtils.getUuid());
      ////                return resourceByMD5;
      //            } else {
      //                resource.setMd5(md5);
      //            }
      resource.setMd5(md5);
    } catch (IOException e) {
      e.printStackTrace();
    }

    VideoSize mediaSize = FfmpegUtils.getMediaSize(file);
    resource.setWidth(mediaSize.getWidth());
    resource.setHeight(mediaSize.getHeight());
    resource.setFileType(Constant.IMAGE.getValue());
    resource.setContentType(contentType);
    resource.setFileSize(file.length());
    resource.setUuid(UUIDUtils.getUuid());
    resource.setNameCn(file.getName());
    resource.setSuffix(LocalFileUtils.getFileSuffix(file));
    resource.setCreatedBy("greatli");
    resource.setUpdatedBy("greatli");

    // 3-处理缩略图
    File thumbnail = FfmpegUtils.createImageThumbnail(file);
    String iconId = saveToStore(thumbnail, resource);
    resource.setIcon(
        host
            + Constant.CMS_STORAGE.getValue()
            + Constant.IMAGE_ICON_PATH.getValue()
            + resource.getUuid()
            + "/"
            + iconId);
    log.info("SAVE THUMBNAIL URL IS : {}", resource.getIcon());

    // 4-存储实体数据
    String fileId = saveToStore(file, resource);
    resource.setFilePath(
        host
            + Constant.CMS_STORAGE.getValue()
            + Constant.IMAGE_FILE_PATH.getValue()
            + resource.getUuid()
            + "/"
            + fileId);
    resource.setFileId(fileId);
    log.info("SAVE FILE URL IS : {}", resource.getFilePath());
    return resource;
  }
}
