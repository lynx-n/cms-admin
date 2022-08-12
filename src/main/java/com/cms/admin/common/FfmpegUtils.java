package com.cms.admin.common;

import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.ScreenExtractor;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;
import ws.schild.jave.info.VideoSize;

import java.io.File;

@Slf4j
public class FfmpegUtils {

    /**
     * 创建视频前10s的动图
     *
     * @param sourceFile
     * @return 生成的路径地址
     */
    public static File createVideoWebP(File sourceFile) {
        log.info("CREATE VIDEO WEBP BEGIN");

        String sourcePathName = sourceFile.getName();
        String fileName = sourcePathName.substring(0, sourcePathName.lastIndexOf("."));
        String targetPath = LocalFileUtils.creatFile(fileName, ".webp");
        File file = new File(targetPath);
        try {
            MultimediaObject multimediaObject = new MultimediaObject(sourceFile);
            MultimediaInfo multimediaInfo = multimediaObject.getInfo();
            // 视频的长度
            long duration = multimediaInfo.getDuration();

            // 视频属性设置
            VideoAttributes videoAttributes = new VideoAttributes();
            videoAttributes.setCodec(Constant.WEBP.getValue());

            // 转码设置 截取视频前10s的作为动图
            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setDuration(Math.min(0.1f, duration));
            encodingAttributes.setVideoAttributes(videoAttributes);

            // 视频转码
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, file, encodingAttributes);

        } catch (EncoderException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 截取视频的缩略图-在1s的时候截取
     *
     * @param sourceFile
     * @return
     */
    public static File createVideoThumbnail(File sourceFile) {
        log.info("CREAT VIDEO THUMBNAIL BEGIN");
        String sourcePathName = sourceFile.getName();
        String fileName = sourcePathName.substring(0, sourcePathName.lastIndexOf("."));
        String targetPath = LocalFileUtils.creatFile(fileName, ".jpg");
        File file = new File(targetPath);
        try {
            MultimediaObject multimediaObject = new MultimediaObject(sourceFile);
            ScreenExtractor extractor = new ScreenExtractor();
            extractor.renderOneImage(multimediaObject, Constant.THUMBNAIL_DEFAULT_VALUE.getIntValue(), Constant.THUMBNAIL_DEFAULT_VALUE.getIntValue(), 1000, file, 1);
        } catch (EncoderException e) {
            log.error("CREAT VIDEO THUMBNAIL ERROR:{}", e.getMessage());
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 获取图片的缩略图-通过视频的方式
     *
     * @param sourceFile
     * @return
     */
    public static File createImageThumbnail(File sourceFile) {
        log.info("CREAT IMAGE THUMBNAIL BEGIN");
        String sourcePathName = sourceFile.getName();
        String fileName = sourcePathName.substring(0, sourcePathName.lastIndexOf("."));
        String targetPath = LocalFileUtils.creatFile(fileName, ".jpg");
        File file = new File(targetPath);
        try {

            MultimediaObject multimediaObject = new MultimediaObject(sourceFile);
            // 视频属性设置
            VideoAttributes videoAttributes = new VideoAttributes();
            videoAttributes.setSize(new VideoSize(Constant.THUMBNAIL_DEFAULT_VALUE.getIntValue(), Constant.THUMBNAIL_DEFAULT_VALUE.getIntValue()));

            // 转码设置 截取截图
            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setVideoAttributes(videoAttributes);
            encodingAttributes.setDuration(0.01F);
            // 视频转码
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, file, encodingAttributes);
        } catch (EncoderException e) {
            log.error("CREAT IMAGE THUMBNAIL ERROR:{}", e.getMessage());
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 得到视频图片大小
     *
     * @param file
     * @return
     */
    public static VideoSize getMediaSize(File file) {
        MultimediaObject multimediaObject = new MultimediaObject(file);
        VideoInfo video = null;
        try {
            video = multimediaObject.getInfo().getVideo();
        } catch (EncoderException e) {
            log.error("GET MEDIA SIZE ERROR:{}", e.getMessage());
            return new VideoSize(0, 0);
        }
        return video.getSize();
    }


    public static void main(String[] args) {
        File file = new File("/Users/greatli/Downloads/测试.jpg");
        File file2 = new File("/Users/greatli/Downloads/164.mp4");
        VideoSize mediaSize = getMediaSize(file);
        System.out.println(mediaSize);
    }

}
