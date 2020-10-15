/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.Menu;
import com.shenzc.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:44
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectMenusByMenuId(List<String> menuIdList);

    List<MenuVo> getMenuList(MenuVo menuVo);

    List<MenuVo> getParentMenuList();

    Integer selectByParentMenuId(String menuId);

    int deleteByMenuId(String menuId);
}
