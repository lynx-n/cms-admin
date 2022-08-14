package com.cms.admin.params;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/** 注册参数实体 */
@Data
public class UserAddParams {
  /** 用户名 */
  @NotNull(message = "用户名或者密码不能为空")
  @Length(min = 3, max = 15, message = "用户名长度3到15")
  String username;

  /** 密码 */
  @NotNull(message = "用户名或者密码不能为空")
  @Length(min = 6, max = 15, message = "密码长度6到15")
  String password;

  /** 角色id 默认为1-系统普通用户 */
  Integer roleId = 1;
}
