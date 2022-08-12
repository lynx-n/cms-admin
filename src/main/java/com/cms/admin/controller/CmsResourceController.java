package com.cms.admin.controller;

import com.cms.admin.common.Constant;
import com.cms.admin.common.FileConstant;
import com.cms.admin.common.LocalFileUtils;
import com.cms.admin.common.ResponseUtils;
import com.cms.admin.entity.CmsResource;
import com.cms.admin.entity.ResponseEntity;
import com.cms.admin.handler.AbstractHandlerFactory;
import com.cms.admin.service.CmsResourceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.File;


/**
 * @author greatli
 * @ClassName CmsResourceController.java
 * @Description 资源处理
 * @createTime 2021年12月26日 10:18:00
 */
@RestController
@RequestMapping("/api/v1/Resource")
@Api(value = "资源管理",tags = "资源管理")
public class CmsResourceController {

    @Resource
    AbstractHandlerFactory factory;

    @Resource
    CmsResourceService resourceService;

    @ApiOperation("查询资源列表-分页查询")
    @GetMapping("/{page}/{size}")
    public ResponseEntity<Object> selectResourcePage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        PageInfo<CmsResource> pageInfo = resourceService.selectResourcePage(page, size);
        return ResponseUtils.success(pageInfo);
    }

    @ApiOperation("资源上传")
    @PostMapping("/upload/{fileType}")
    private ResponseEntity<Object> saveResource(@RequestParam("file") MultipartFile file, @PathVariable("fileType") String fileType) {
        if (!FileConstant.fileMap.containsKey(fileType)) {
            return ResponseUtils.badRequest(Constant.PARAMS_ERROR.getValue());
        }
        // 1：先将文件转换成File
        File localFile = LocalFileUtils.transMultipartFileToFile(file);

        //2： 处理不同的文件
        CmsResource resource = factory.getHandlerBean(FileConstant.fileMap.get(fileType)).dealResource(localFile, file.getContentType());

        //3: 文件入库
        resourceService.insertCmsResource(resource);

        //4：将文件删除
        return ResponseUtils.success(resource);
    }

    @ApiOperation("删除资源")
    @DeleteMapping("/{uuid}")
    private ResponseEntity<String> deleteResource(@PathParam("uuid") String uuid) {
        return ResponseUtils.success("");
    }

}
