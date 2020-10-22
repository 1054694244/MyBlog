/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.controller;

import com.shenzc.entity.backendUser.Menu;
import com.shenzc.resutl.CommonPage;
import com.shenzc.resutl.ResultBody;
import com.shenzc.service.MenuService;
import com.shenzc.service.SSOService;
import com.shenzc.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:43
 */
@RestController
@RequestMapping("/manage/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private SSOService ssoService;

    @PostMapping("/getList")
    public ResultBody getList(@RequestBody MenuVo menuVo){
        List<MenuVo> list = menuService.getList(menuVo);
        return ResultBody.success(CommonPage.restPage(list));
    }

    @GetMapping("/getMenu")
    public ResultBody getMenu(@RequestParam(value = "roleId")String roleId){
        //String menuJson = ssoService.getMenu(roleId);
        return ResultBody.success(ssoService.getMenu(roleId));
    }

    @GetMapping("/getParentMenu")
    public ResultBody getParentMenuList(){
        return ResultBody.success(menuService.getParentMenuList());
    }

    @PostMapping("addMenu")
    public ResultBody addMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
        return ResultBody.success("添加成功");
    }

    @PostMapping("updateMenu")
    public ResultBody updateMenu(@RequestBody Menu menu){
        menuService.editMenu(menu);
        return ResultBody.success("跟新成功");
    }

    @GetMapping("deleteMenu")
    public ResultBody deleteMenu(String menuId){
       try {
           menuService.deleteMenu(menuId);
           return ResultBody.success("删除成功");
       }catch (RuntimeException e){
           return ResultBody.fail(200,e.getMessage());
       }
    }
}
