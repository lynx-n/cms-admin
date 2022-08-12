package com.cms.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class LocalFileUtils {

    public static String creatFile(String fileName, String suffix) {
        Date data = new Date();
        String basePath = System.getProperty("user.dir") + "/temp/";
        String path = basePath + data.getTime() + "/" + UUIDUtils.getUuid() + "/" + fileName + suffix;
        File file = new File(path);
        try {
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                parentFile.mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getPath();
    }

    public static File transMultipartFileToFile(MultipartFile multipartFile) {
        String fileRealName = multipartFile.getOriginalFilename();//获得原始文件名;
        String fileName = fileRealName.substring(0, fileRealName.lastIndexOf("."));//点号的位置
        String suffix = fileRealName.substring(fileRealName.lastIndexOf("."));//截取文件后缀
        File file = new File(creatFile(fileName, suffix));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("TRANS MULTIPARTFILE TO FILE ERROR:{}", e.getMessage());
            return null;
        }

        return file;
    }

    public static String getFileSuffix(File file) {
        String fileRealName = file.getName();//获得原始文件名;
        int fileName = fileRealName.lastIndexOf(".");//点号的位置
        String suffix = fileRealName.substring(fileName);//截取文件后缀
        return suffix;
    }


}
