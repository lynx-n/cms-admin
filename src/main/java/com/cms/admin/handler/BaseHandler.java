package com.cms.admin.handler;

import com.cms.admin.entity.ResourceEntity;

import java.io.File;

/**
 * @author greatli
 * @ClassName BaseHandler.java
 * @Description BaseHandler
 * @createTime 2021年12月25日 21:30:00
 */
public interface BaseHandler {

    boolean checkFile(File file);

    /**
     * 文件处理
     * @param file
     */
    ResourceEntity dealResource(File file, String contentType);
}
