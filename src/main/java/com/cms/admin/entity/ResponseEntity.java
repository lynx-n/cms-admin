package com.cms.admin.entity;

import lombok.Data;

/**
 * @author greatli
 * @ClassName ResponseEntity.java
 * @Description TODO
 * @createTime 2021年12月26日 10:21:00
 */
@Data
public class ResponseEntity<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseEntity() {
    }
}
