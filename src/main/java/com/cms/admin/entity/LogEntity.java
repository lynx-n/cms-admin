package com.cms.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {

  /** 日志序号 */
  private Integer id;

  /** 操作类型 */
  private String operationType;

  /** 操作者ip */
  private String requestIp;

  /** 调用接口 */
  private String requestUrl;

  /** 操作内容 */
  private String operationContent;

  /** 操作者id */
  private Integer operationUserId;

  /** 创建时间 */
  private String operationTime;

  /** http方法 GET POST PUT DELETE PATCH */
  private String httpMethod;

  /** 类方法 */
  private String classMethod;

  /** 请求参数 */
  private String requestParams;

  /** 响应结果 */
  private String resultMsg;

  /** 接口耗时 */
  private Long timeCost;

  /** 接口响应码 */
  private int resultCode;
}
