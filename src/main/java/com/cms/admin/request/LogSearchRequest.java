package com.cms.admin.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author ：GreatLi
 * @date ：Created 2022/10/2 21:26
 * @description：日志查询
 */
@Data
public class LogSearchRequest {

  /** 操作类型 */
  private String operationType;

  /** 操作内容 */
  private String operationContent;

  /** 页面 */
  @NotBlank
  @Min(0)
  private int pageNo;

  /** 页面大小 */
  @NotBlank private int pageSize;
}
