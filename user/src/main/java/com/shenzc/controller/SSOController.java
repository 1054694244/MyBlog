/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.controller;

import com.shenzc.resutl.ResultBody;
import com.shenzc.entity.backendUser.User;
import com.shenzc.service.SSOService;
import com.shenzc.utils.JwtUtil;
import com.shenzc.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/3 16:41
 */
@RestController
@RequestMapping("/manage/sso")
public class SSOController {

    public static final Logger logger = LoggerFactory.getLogger(SSOController.class);

    @Autowired
    private SSOService ssoService;

    @PostMapping("/loginIn")
    public ResultBody userLogin(@RequestBody UserVo user){
        try {
            User loginUser = ssoService.login(user);
            if (loginUser!=null){
                //返回token
                String token = JwtUtil.sign(loginUser);
                if (token != null) {
                    Map<String,String> returnMap = new HashMap<>();
                    returnMap.put("token",token);
                    returnMap.put("username",loginUser.getUsername());
                    returnMap.put("roleId",loginUser.getRoleId());
                    return ResultBody.success(returnMap);
                }
            }
        }catch (Exception e){
            logger.error("登录失败",e);
        }
        return ResultBody.fail(250,"登录失败！！！");
    }

    @GetMapping("/getCode")
    public ResultBody getCode(@RequestParam(value = "phone")String phone){
        String code = "";
        try {
            code = ssoService.getCode(phone);
        }catch (Exception e){
            logger.error("获取验证码失败",e);
        }
        return ResultBody.success(code);
    }

}
