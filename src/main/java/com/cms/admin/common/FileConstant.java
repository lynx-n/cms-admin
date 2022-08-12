package com.cms.admin.common;

import java.util.HashMap;
import java.util.Map;

public class FileConstant {
    public static Map<String, String> fileMap = new HashMap<String, String>() {{
        put(Constant.IMAGE.getValue(), Constant.IMAGE_HANDLER_NAME.getValue());
        put(Constant.TEXT.getValue(), Constant.TEXT_HANDLER_NAME.getValue());
        put(Constant.VIDEO.getValue(), Constant.VIDEO_HANDLER_NAME.getValue());

    }};
}
