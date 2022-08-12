package com.cms.admin.entity;

import java.util.Date;

public class CmsLogEntity {


    /**
     * 日志序号
     */
    private Integer id;

    /**
     * 操作类型
     */
    private String operationType;

    /**
     * 操作内容
     */
    private String operation;

    /**
     * 操作结果
     */
    private String operationResult;

    /**
     * 操作者
     */
    private String operationUser;

    /**
     * 操作者id
     */
    private Integer operationUserId;

    /**
     * 创建时间
     */
    private Date operationTime;
}
