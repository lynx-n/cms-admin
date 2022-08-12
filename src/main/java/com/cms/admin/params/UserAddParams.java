package com.cms.admin.params;

import lombok.Data;

/**
 * 注册参数实体
 */
@Data
public class UserAddParams {
    /**
     * 用户名
     */
    String username;
    /**
     * 密码
     */
    String password;

    /**
     * 角色id
     */
    Integer roleId;
}
