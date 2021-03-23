package com.shenzc.artiicleCategory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzc.artiicleCategory.entity.FrontMenuVo;
import com.shenzc.artiicleCategory.mapper.FrontMenuMapper;
import com.shenzc.entity.front.FrontMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrontMenuService {

    @Autowired
    private FrontMenuMapper frontMenuMapper;

    /**
     * 查询所有类型
     * @return
     */
    public List<FrontMenuVo> getAllMenu() {
        //查询出所有父菜单
        List<FrontMenu> frontMenus = frontMenuMapper.selectList(new QueryWrapper<FrontMenu>().eq("parent_menu_id","0"));
        List<FrontMenuVo> frontMenuVos = new ArrayList<>();
        frontMenus.stream().forEach(frontMenu -> {
            FrontMenuVo frontMenuVo = new FrontMenuVo();
            BeanUtils.copyProperties(frontMenu,frontMenuVo);
            //查询父菜单对应的子菜单
            List<FrontMenu> childList = frontMenuMapper.selectList(new QueryWrapper<FrontMenu>().eq("parent_menu_id", frontMenu.getMenuId()));
            if (childList.size()!=0 && childList!=null){
                frontMenuVo.setChildFrontMenu(childList);
            }
            frontMenuVos.add(frontMenuVo);
        });
        return frontMenuVos;
    }
}
