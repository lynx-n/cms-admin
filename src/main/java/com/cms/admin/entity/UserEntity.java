package com.cms.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * cms_user
 *
 * @author
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

  private Integer id;

  /** 用户名 */
  private String userName;

  /** 密码 */
  @JsonIgnore private String password;

  /** 年纪 */
  private int age;

  /** 用户头像 */
  private String avatar;

  /** 手机号 */
  private String phone;

  /** 创建者 */
  private String createdBy;

  /** 更新者 */
  private String updatedBy;

  /** 创建时间 */
  private Date createdTime;

  /** 更新时间 */
  private Date updatedTime;

  /** 加密key值 */
  @JsonIgnore private String encryptKey;

  private static final long serialVersionUID = 1L;
}
