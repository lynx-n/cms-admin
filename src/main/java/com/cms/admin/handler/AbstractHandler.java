package com.cms.admin.handler;

import com.cms.admin.common.Constant;
import com.cms.admin.entity.CmsResource;
import com.cms.admin.service.MongoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author greatli
 * @ClassName AbstractHandler.java
 * @Description
 * @createTime 2021年12月25日 21:29:00
 */
@Component
public abstract class AbstractHandler implements BaseHandler {


    @Resource
    MongoService mongoService;

    /**
     * 将文件存储到mongoDB
     *
     * @return
     */
    public String saveToStore(File file, CmsResource cmsResource) {
        String objectId = "";
        if (file.length() >= Constant.FILE_SIZE_16M.getIntValue()) {
            objectId = mongoService.saveBigFile(file, cmsResource);
        } else {
            objectId =mongoService.saveSmallFile(file, cmsResource);
        }
        return objectId;
    }
}
