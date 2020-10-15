/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.shenzc.entity.Menu;
import com.shenzc.mapper.MenuMapper;
import com.shenzc.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:44
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过查询条件查看菜单信息
     * @param menuVo
     * @return
     */
    public List<MenuVo> getList(MenuVo menuVo){
        PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<MenuVo> menuList = menuMapper.getMenuList(menuVo);
        return menuList;
    }

    /**
     * 获取所有父菜单
     * @return
     */
    public List<MenuVo> getParentMenuList(){
        List<MenuVo> menuList = menuMapper.getParentMenuList();
        return menuList;
    }

    /**
     * 添加菜单
     * @param menu
     */
    public void addMenu(Menu menu){
        if (menu.getId()!=null){
            menuMapper.updateById(menu);
        }else {
            if ("".equals(menu.getParentId())){
                menu.setParentId("0");
            }
            menuMapper.insert(menu);
        }
    }

    /**
     * 跟新菜单
     * @param menu
     */
    public void editMenu(Menu menu){
        menuMapper.update(menu,new UpdateWrapper<Menu>().eq("menu_id",menu.getMenuId()));
    }

    /**
     * 删除菜单
     * @param menuId
     */
    public void deleteMenu(String menuId){
        //判断是否有此菜单是否存在子菜单
        if (menuMapper.selectByParentMenuId(menuId)>0){
            throw new RuntimeException("此菜单存在子菜单");
        };
        menuMapper.deleteByMenuId(menuId);
    }

}
