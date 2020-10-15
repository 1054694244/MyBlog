/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.controller;

import com.shenzc.resutl.CommonPage;
import com.shenzc.resutl.ResultBody;
import com.shenzc.service.RoleService;
import com.shenzc.service.UserService;
import com.shenzc.vo.RoleVo;
import com.shenzc.vo.TreeVo;
import com.shenzc.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:43
 */
@RestController
@Slf4j
@RequestMapping("/manage/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/getRoleList")
    public ResultBody getRoleList(@RequestBody RoleVo roleVo){
        return roleService.getAllRole(roleVo);
    }


    @PostMapping("/insertOrUpdateRole")
    public ResultBody insertOrUpdateRole(@RequestBody RoleVo roleVo){
        roleService.insertOrUpdateRole(roleVo);
        return ResultBody.success("添加成功");
    }

    @GetMapping("deleteRole")
    public ResultBody deleteRole(String id){
       try {
           roleService.deleteRole(id);
           return ResultBody.success("删除成功");
       }catch (RuntimeException e){
           return ResultBody.fail(200,e.getMessage());
       }
    }

    @GetMapping("/getTreeList")
    public ResultBody getTreeList(String roleId){
        Map<String,Object> map = new HashMap<>();
        List<TreeVo> treeVoList = roleService.getTreeVoList();
        List<String> selectTreeId = roleService.getSelectTreeId(roleId);
        map.put("all",treeVoList);
        map.put("selected",selectTreeId);
        return ResultBody.success(map);
    }

    @PostMapping("/addRoleMenu")
    public ResultBody addRoleMenu(String roleId,@RequestBody List<TreeVo> treeVoList){
        log.info("修改角色菜单参数: "+treeVoList.toString());
        log.info("修改角色菜单参数: "+roleId);
        try {
            roleService.addRoleMenu(roleId,treeVoList);
        }catch (Exception e){
            log.info(e.getMessage(),e);
            return ResultBody.fail500("保存失败");
        }
        return ResultBody.success("保存成功");
    }
}
