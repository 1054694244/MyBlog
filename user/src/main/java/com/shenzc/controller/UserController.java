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
import com.shenzc.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:43
 */
@RestController
@Slf4j
@RequestMapping("/manage/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/getUserList")
    public ResultBody getList(@RequestBody UserVo userVo){
        List<UserVo> list = userService.getUserList(userVo);
        return ResultBody.success(CommonPage.restPage(list));
    }


    @GetMapping("/getRole")
    public ResultBody getParentMenuList(){
        return roleService.getAllRole(new RoleVo());
    }

    @PostMapping("/insertOrUpdateUser")
    public ResultBody insertOrUpdateUser(@RequestBody UserVo userVo){
        userService.insertOrUpdate(userVo);
        return ResultBody.success("添加成功");
    }

    @GetMapping("deleteUser")
    public ResultBody deleteUser(String id){
       try {
           userService.deleteUser(id);
           return ResultBody.success("删除成功");
       }catch (RuntimeException e){
           return ResultBody.fail(200,e.getMessage());
       }
    }
}
