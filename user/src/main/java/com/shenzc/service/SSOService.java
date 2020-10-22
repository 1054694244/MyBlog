/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzc.constant.URLConstant;
import com.shenzc.entity.backendUser.Menu;
import com.shenzc.entity.backendUser.Role;
import com.shenzc.entity.backendUser.RoleMenu;
import com.shenzc.entity.backendUser.User;
import com.shenzc.mapper.MenuMapper;
import com.shenzc.mapper.RoleMapper;
import com.shenzc.mapper.RoleMenuMapper;
import com.shenzc.mapper.SSOMapper;
import com.shenzc.vo.MenuVo;
import com.shenzc.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 14:19
 */
@Service
public class SSOService {

    private static final Logger logger = LoggerFactory.getLogger(SSOService.class);

    @Autowired
    private SSOMapper ssoMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HttpAPIService httpAPIService;


    /**
     * 登录，如果账号密码正确，则返回菜单，反之null
     * @param user·
     * @return
     */
    public User login(UserVo user){
        User loginUser = null;
        //判断是密码登录还是手机验证登录
        if (!"".equals(user.getCode()) || user.getCode() == null){
            loginUser = ssoMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()).eq("password", user.getPassword()));
        }else {
            //从redis里面获取code
            try {
                String code = stringRedisTemplate.opsForValue().get(user.getPhone());
                if (code.equals(user.getCode())){
                    loginUser = ssoMapper.selectOne(new QueryWrapper<User>().eq("phone",user.getPhone()));
                }
            }catch (Exception e){
                throw new RuntimeException("redis获取验证码失败",e);
            }
        }

        if (loginUser!=null){
            //统计登录次数
            ssoMapper.updateUser(loginUser.getId(),loginUser.getLoginCount());
            return loginUser;
        }else {
            return null;
        }
    }

    /**
     * 获取当前登录人员所属岗位，及其对应的菜单
     * @param roleId
     * @return
     */
    public List<MenuVo> getMenu(String roleId){
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id", roleId));
        List<RoleMenu> roleMenuList = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id", role.getRoleId()));
        List<String> menuIdList = roleMenuList.stream().map(menu -> menu.getMenuId()).collect(Collectors.toList());
        List<Menu> menuList = menuMapper.selectMenusByMenuId(menuIdList);
        List<Menu> parentMenuList = menuList.stream().filter(menu -> "0".equals(menu.getParentId())).collect(Collectors.toList());
        List<MenuVo> menuVoList = new ArrayList<>();
        parentMenuList.stream().forEach(menu -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu,menuVo);
            menuVoList.add(menuVo);
        });
        List<Menu> childMenuList = menuList.stream().filter(menu -> !"0".equals(menu.getParentId())).collect(Collectors.toList());
        Map<String,List<Menu>> menuMap = childMenuList.stream().collect(Collectors.groupingBy(Menu::getParentId));
        for (MenuVo menuVo:menuVoList) {
            for (String key:menuMap.keySet()){
                if (key.equals(menuVo.getMenuId())){
                    menuVo.setMenuList(menuMap.get(key));
                }
            }
        }
        return menuVoList;
    }

    public User getUser(String userId) {
        return ssoMapper.selectOne(new QueryWrapper<User>().eq("user_id",userId));
    }

    /**
     * 生成验证码，并存到redis里面 5分钟有效
     * @param phone
     */
    public String getCode(String phone) {
        //随机生成六位数
        int num = (int)((Math.random()*9+1)*100000);
        String code = String.valueOf(num);
        try {
            stringRedisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
        }catch (Exception e){
            throw new RuntimeException("redis设置验证码失败",e);
        }
        //使用aliyun发送短信
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("code",code);
        paramMap.put("phone",phone);
        try{
            httpAPIService.doGet(URLConstant.messageUrl, paramMap);
        }catch (Exception e){
            throw new RuntimeException("调用阿里云短信接口异常",e);
        }
        return code;
    }
}
