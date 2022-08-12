package com.cms.admin.params;


import lombok.Data;

@Data
public class UserLoginParams {
    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;
}
