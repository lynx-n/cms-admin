package com.cms.admin.service;

import com.cms.admin.entity.CmsUser;
import com.cms.admin.vo.CmsUserVO;

public interface CmsUserService {

   /**
    * 通过用户名查询
    * @param username
    * @return
    */
   CmsUser selectCmsUserByUsername(String username);

   /**
    * 插入一个用户
    * @param cmsUser
    * @return
    */
   int insertCmsUser(CmsUser cmsUser);

   /**
    * 根据用户id查询用户角色信息
    * @param id
    * @return
    */
   CmsUserVO selectCmsUserVOById(Integer id);

}
