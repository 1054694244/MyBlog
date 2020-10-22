/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.service;

import com.github.pagehelper.PageHelper;
import com.shenzc.mapper.SSOMapper;
import com.shenzc.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/24 9:14
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private SSOMapper ssoMapper;

    /**
     * 根据条件查询用户
     * @param userVo
     * @return
     */
    public List<UserVo> getUserList(UserVo userVo){
        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<UserVo> userVos = ssoMapper.selectByUser(userVo);
        return userVos;
    }

    public void insertOrUpdate(UserVo userVo){
        if ("".equals(userVo.getId()) || userVo.getId()==null){
            userVo.setLoginCount(1);
            ssoMapper.insert(userVo);
        }else {
            ssoMapper.updateById(userVo);
        }
    }

    public void deleteUser(String id){
        ssoMapper.deleteById(id);
    }
}
