package com.cms.admin.filter;

import com.cms.admin.common.Constant;
import com.cms.admin.entity.ResourceEntity;
import com.cms.admin.service.CmsResourceService;
import com.cms.admin.service.MongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class FileFilter implements Filter {

    @Resource
    private CmsResourceService resourceService;

    @Resource
    private MongoService mongoService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String uri = httpRequest.getRequestURI();
        int badRequest = HttpServletResponse.SC_BAD_REQUEST;
        int scNotFound = HttpServletResponse.SC_NOT_FOUND;
        try {
            // 进入下载
            if (uri != null && uri.contains(Constant.CMS_STORAGE.getValue())) {
                // 下载资源
                String uuid = getUuid(uri);
                ResourceEntity resource = resourceService.selectResourceByUuid(uuid);
                if (uuid == null || resource == null) {
                    httpResponse.setStatus(scNotFound);
                    return;
                } else {
                    String fileId = uri.substring(uri.lastIndexOf("/") + 1);
                    byte[] bytes = mongoService.download(fileId, resource);
                    ServletOutputStream outputStream = httpResponse.getOutputStream();
                    outputStream.write(bytes);
                    return;
                }

            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (IOException | ServletException e) {
            log.error("DOWNLOAD SOURCE ERROR:{}", e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

    public String getUuid(String url) {
        int index = url.lastIndexOf("/");
        String uri = url.substring(0, index);
        String uuid = uri.substring(uri.lastIndexOf("/") + 1);
        return uuid;
    }

}
