package com.cms.admin.service.impl;

import com.cms.admin.entity.UserEntity;
import com.cms.admin.mapper.CmsUserMapper;
import com.cms.admin.service.CmsUserService;
import com.cms.admin.vo.CmsUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CmsUserServiceImpl implements CmsUserService {

  @Resource private CmsUserMapper userMapper;

  @Override
  public UserEntity selectCmsUserByUsername(String username) {
    return userMapper.selectCmsUserByUsername(username);
  }

  @Override
  public int insertCmsUser(UserEntity userEntity) {
    log.info("enter insert cms user service");
    return userMapper.insertCmsUser(userEntity);
  }

  @Override
  public CmsUserVO selectCmsUserById(Integer id) {
    return userMapper.selectCmsUserVOById(id);
  }
}
