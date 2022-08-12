package com.cms.admin.common;

import com.cms.admin.entity.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * @author greatli
 * @ClassName ResponseUtils.java
 * @Description 请求响应体
 * @createTime 2021年12月26日 10:20:00
 */
public class ResponseUtils<T> {
    /**
     * 无权限 403
     *
     * @param data
     * @return
     */
    public static <T> ResponseEntity<T> forbidden(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(HttpStatus.FORBIDDEN.value());
        entity.setMsg(HttpStatus.FORBIDDEN.getReasonPhrase());
        entity.setData(data);
        return entity;
    }

    /**
     * 未登陆 401
     *
     * @param data
     * @return
     */
    public static <T> ResponseEntity<T> unauthorized(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(HttpStatus.UNAUTHORIZED.value());
        entity.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        entity.setData(data);
        return entity;
    }

    /**
     * 错误请求 400
     *
     * @param data
     * @return
     */
    public static <T> ResponseEntity<T> badRequest(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(HttpStatus.BAD_REQUEST.value());
        entity.setMsg(HttpStatus.BAD_REQUEST.getReasonPhrase());
        entity.setData(data);
        return entity;
    }


    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(HttpStatus.OK.value());
        entity.setMsg(Constant.RESPONSE_SUCCESS.getValue());
        entity.setData(data);
        return entity;
    }

    public static <T> ResponseEntity<T> error(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        entity.setMsg(Constant.PARAMS_ERROR.getValue());
        entity.setData(data);
        return entity;
    }
}
