package com.cms.admin.handler;

import com.cms.admin.common.Constant;
import com.cms.admin.handler.impl.ImageHandler;
import com.cms.admin.handler.impl.TextHandler;
import com.cms.admin.handler.impl.VideoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author greatli
 * @ClassName AbstractoryHandlerFatory.java
 * @Description TODO
 * @createTime 2021年12月25日 21:13:00
 */
@Component
public class AbstractHandlerFactory {


    @Autowired
    private ApplicationContext applicationContext;
    private Map<String, AbstractHandler> handlerMap;

    public AbstractHandlerFactory() {
        this.handlerMap = new HashMap<>();
    }

    public AbstractHandler getHandlerBean(String beanName) {
        return handlerMap.get(beanName);
    }

    @PostConstruct
    public void init() {
        handlerMap.put(Constant.VIDEO_HANDLER_NAME.getValue(), applicationContext.getBean(VideoHandler.class));
        handlerMap.put(Constant.TEXT_HANDLER_NAME.getValue(), applicationContext.getBean(TextHandler.class));
        handlerMap.put(Constant.IMAGE_HANDLER_NAME.getValue(), applicationContext.getBean(ImageHandler.class));
    }

}
