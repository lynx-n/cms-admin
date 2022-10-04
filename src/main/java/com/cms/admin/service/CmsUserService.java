package com.cms.admin.service;

import com.cms.admin.entity.UserEntity;
import com.cms.admin.vo.CmsUserVO;

public interface CmsUserService {

   /**
    * 通过用户名查询
    * @param username
    * @return
    */
   UserEntity selectCmsUserByUsername(String username);

   /**
    * 插入一个用户
    * @param userEntity
    * @return
    */
   int insertCmsUser(UserEntity userEntity);

   /**
    * 根据用户id查询用户角色信息
    * @param id
    * @return
    */
   CmsUserVO selectCmsUserById(Integer id);

}
