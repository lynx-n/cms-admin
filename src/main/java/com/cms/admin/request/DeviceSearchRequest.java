package com.cms.admin.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author ：GreatLi
 * @date ：Created 2022/10/2 20:42
 * @description：设备查询
 */
@Data
public class DeviceSearchRequest {
  /** 设备主键id */
  private Integer id;

  /** 设备id */
  private String deviceNumber;

  /** 设备ip地址 */
  private String deviceIp;

  /** 设备mac地址 */
  private String deviceMac;

  /** 创建者 */
  private String createdBy;

  /** 页面 */
  @NotBlank
  @Min(0)
  private int pageNo;

  /** 页面大小 */
  @NotBlank private int pageSize;
}
