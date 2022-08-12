package com.cms.admin.common;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 生成设备id
     *
     * @return
     */
    public static String getDeviceId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").toUpperCase();
    }


    /**
     * 生成用户id
     *
     * @return
     */
    public static String getUserId() {
        String uuid = UUID.randomUUID().toString();
        String[] ids = uuid.split("-");
        String userId = ids[0] + ids[1] + "~" + ids[3].charAt(2) + ids[3].charAt(3);
        return userId;
    }

    public static String getEncryptKey() {
        String uuid = UUID.randomUUID().toString();
        String[] ids = uuid.split("-");
        String key = ids[0] + ids[3] + ids[2];
        return key;
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

}
