package com.cms.admin.mapper;

import com.cms.admin.entity.MenuAuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CmsMenuAuthorityMapper {
  int deleteMenuByIds(@Param("ids") List<Integer> ids);

  int insert(MenuAuthorityEntity record);

  int insertMenus(List<MenuAuthorityEntity> entityList);

  MenuAuthorityEntity selectMenuById(Integer id);

  List<MenuAuthorityEntity> selectMenus();

  int updateMenus(List<MenuAuthorityEntity> entityList);
}
