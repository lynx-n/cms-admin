package com.cms.admin.service;


import com.cms.admin.common.Constant;
import com.cms.admin.common.UUIDUtils;
import com.cms.admin.entity.ResourceEntity;
import com.cms.admin.entity.SmallFileInfo;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsUpload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;

@Slf4j
@Component
public class MongoService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    GridFsTemplate gridFsTemplate;

    @Resource
    GridFSBucket gridFSBucket;

    /**
     * 存储小文件-小于16M
     *
     * @param file
     * @param
     * @return
     */
    public String saveSmallFile(File file, ResourceEntity resource) {
        log.info("SAVE SMALL FILE , SIZE:{} , NAME:{}", file.length(), file.getName());
        SmallFileInfo smallFileInfo = new SmallFileInfo();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            smallFileInfo.setContent(new Binary(BsonBinarySubType.BINARY, bytes));
            String uuid = UUIDUtils.getUuid();
            smallFileInfo.setFileId(uuid);
            smallFileInfo.setUserAccount(resource.getCreatedBy());
            smallFileInfo.setContentType(resource.getContentType());
            smallFileInfo.setFileName(resource.getNameCn());
            SmallFileInfo info = mongoTemplate.save(smallFileInfo);
            smallFileInfo.setId(info.getId());
        } catch (IOException e) {
            log.error("SAVE SMALL FILE ERROR,{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
        return smallFileInfo.getId();
    }

    /**
     * 存储大文件
     *
     * @param file
     * @param resource
     * @return
     */
    public String saveBigFile(File file, ResourceEntity resource) {
        log.info("SAVE BIG FILE , SIZE:{} , NAME:{}", file.length(), file.getName());
        Document metadata = new Document();
        metadata.put("user_account", resource.getCreatedBy());
        metadata.put("content_type", resource.getContentType());

        String id = "";
        try (FileInputStream in = new FileInputStream(file)) {
            GridFsUpload.GridFsUploadBuilder<ObjectId> builder = GridFsUpload.fromStream(in);
            // 生成上传对象
            GridFsUpload<ObjectId> uploadEntity = builder.metadata(metadata).filename(file.getName()).build();
            id = gridFsTemplate.store(uploadEntity).toHexString();

        } catch (IOException e) {
            log.error("SAVE BIG FILE ERROR:{}", e.getMessage());
        }
        return id;
    }

    public byte[] download(String id, ResourceEntity resource) throws IOException {
        log.info("DOWNLOAD RESOURCE:{}", id);
        byte[] contents;
        if (resource.getFileSize() >= Constant.FILE_SIZE_16M.getIntValue()) {
            // 从FS文件中下载
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))));
            //使用GridFsBucket打开一个下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getId());
            //创建GridFsResource对象，获取流
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            InputStream inputStream = gridFsResource.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[2048];
            while ((len = bufferedInputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byteArrayOutputStream.flush();
            contents = byteArrayOutputStream.toByteArray();
        } else {
            // 从document中下载
            //Query.query(Criteria.where("_id").is(new ObjectId(id)))
            SmallFileInfo fileInfo = mongoTemplate.findById(new ObjectId(id), SmallFileInfo.class, "smallFileInfo");
            contents = fileInfo.getContent().getData();
        }

        // 返回数据流
        return contents;
    }

}
