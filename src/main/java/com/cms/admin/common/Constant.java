package com.cms.admin.common;


/**
 * @author greatli
 * @ClassName Constant.java
 * @Description TODO
 * @createTime 2021年12月25日 21:40:00
 */
public enum Constant {

    /**
     * 响应信息
     */
    PARAMS_ERROR("params error"),
    PARAMS_LEN_ERROR("参数长度过长"),
    PARAMS_ERROR_CN("参数错误"),
    USER_REGISTERED_CN("用户已经注册"),
    USER_USERNAME_PASSWORD_ISNULL_CN("账户或密码为空"),
    USER_USERNAME_PASSWORD_IS_ERROR_CN("账户或密码错误"),


    /**
     * 上传文件常量
     */
//    RESOURCE_TYPE"VIDEO", "video_handler"),
//    RESOURCE_TYPE("IMAGE", "image_handler"),
//    RESOURCE_TYPE("TEXT", "image_handler"),


    VIDEO("video"),
    TEXT("text"),
    IMAGE("image"),


    VIDEO_ICON_PATH("/icon/video/"),
    TEXT_ICON_PATH("/icon/text/"),
    IMAGE_ICON_PATH("/icon/image/"),

    VIDEO_FILE_PATH("/file/video/"),
    TEXT_FILE_PATH("/file/text/"),
    IMAGE_FILE_PATH("/file/image/"),

    CMS_STORAGE("/cms/storage"),

    /**
     * handler 常量
     */
    VIDEO_HANDLER_NAME("video_handler"),

    TEXT_HANDLER_NAME("text_handler"),

    IMAGE_HANDLER_NAME("image_handler"),

    RESPONSE_SUCCESS("success"),
    RESPONSE_ERROR("error"),


    FILE_SIZE_16M(16),

    /**
     * 视频转码常量
     */
    WEBP("webp"),

    THUMBNAIL_DEFAULT_VALUE(255);

    private String constant;
    private Integer intValue;


    Constant(String name) {
        this.constant = name;
    }

    Constant(int i) {
        this.intValue = i;
    }


    public String getValue() {
        return this.constant;
    }

    public Integer getIntValue() {
        return this.intValue;
    }



}
