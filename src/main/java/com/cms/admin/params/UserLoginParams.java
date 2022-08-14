package com.cms.admin.params;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginParams {
  /** 用户名 */
  @NotNull(message = "用户名或者密码不能为空")
  String username;
  /** 密码 */
  @NotNull(message = "用户名或者密码不能为空")
  String password;
}
