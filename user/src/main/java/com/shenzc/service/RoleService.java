/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.shenzc.entity.Role;
import com.shenzc.mapper.MenuMapper;
import com.shenzc.mapper.RoleMapper;
import com.shenzc.mapper.RoleMenuMapper;
import com.shenzc.mapper.SSOMapper;
import com.shenzc.resutl.CommonPage;
import com.shenzc.resutl.ResultBody;
import com.shenzc.vo.RoleVo;
import com.shenzc.vo.TreeVo;
import com.shenzc.vo.UserVo;
import com.sun.deploy.panel.TreeBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/24 9:33
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private SSOMapper ssoMapper;

    public ResultBody getAllRole(RoleVo roleVo){
        if (roleVo.getPage() == null){
            List<RoleVo> roleVos = roleMapper.selectAllRole(roleVo.getRoleName());
            roleVos.stream().forEach(roleVo1->{
                int count = ssoMapper.selectRoleNum(roleVo1.getRoleId());
                roleVo1.setSum(count);
            });
            return ResultBody.success(roleVos);
        }else {
            PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
            List<RoleVo> roleVos = roleMapper.selectAllRole(roleVo.getRoleName());
            roleVos.stream().forEach(roleVo1->{
                int count = ssoMapper.selectRoleNum(roleVo1.getRoleId());
                roleVo1.setSum(count);
            });
            return ResultBody.success(CommonPage.restPage(roleVos));
        }
    }

    public void insertOrUpdateRole(RoleVo roleVo){
        if ("".equals(roleVo.getId()) || roleVo.getId()==null){
            roleMapper.insert(roleVo);
        }else {
            roleMapper.updateById(roleVo);
        }
    }

    public void deleteRole(String id){
        roleMapper.deleteById(id);
    }

    /**
     * 获取所有菜单
     */
    public List<TreeVo> getTreeVoList(){
        List<TreeVo> parentTreeList = roleMapper.getTreeVoList();
        parentTreeList.stream().forEach(parentTree->{
            List<TreeVo> chlidTreeList = roleMapper.getChlidTreeList(parentTree.getId());
            parentTree.setChildren(chlidTreeList);
        });
        return parentTreeList;
    }

    /**
     * 获取角色所有关联菜单
     */
    public List<String> getSelectTreeId(String roleId){
        return roleMapper.getSelectTreeId(roleId);
    }

    @Transactional
    public void addRoleMenu(String roleId, List<TreeVo> treeVoList) {
        try{
            roleMenuMapper.deleteByRoleId(roleId);
        }catch (Exception e){
            throw new RuntimeException("删除角色菜单失败");
        }
        List<TreeVo> treeVos = new ArrayList<>();
        treeVoList.stream().forEach(tree -> {
            TreeVo treeVo = new TreeVo();
            BeanUtils.copyProperties(tree,treeVo);
            treeVos.add(treeVo);
            treeVos.addAll(tree.getChildren());
        });
        //批量插入role_menu
        try{
            roleMenuMapper.batchInsertRoleMenu(roleId,treeVos);
        }catch (Exception e){
            throw new RuntimeException("批量添加角色菜单失败");
        }
    }
}
