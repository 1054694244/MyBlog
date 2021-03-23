/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.artiicleCategory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzc.artiicleCategory.mapper.CategoryMapper;
import com.shenzc.entity.backendUser.Category;
import com.shenzc.utils.FastJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 14:56
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询所有父分类
     * @return
     */
    public List<Category> getParentCategory(){
        return categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_category_id","0"));
    }

    /**
     * 查询大分类下的二级分类
     * @return
     */
    public String getChildCategory(String categoryId){
        List<Category> categoryList = categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_category_id", categoryId));
        List<Map<String,String>> list = new ArrayList<>();
        categoryList.stream().forEach(category -> {
            Map<String,String> map = new HashMap<>();
            map.put("name",category.getCategoryName());
            map.put("value",category.getCategoryId());
            list.add(map);
        });
        return FastJsonUtils.convertObjectToJSON(list);
    }

    /**
     * 查询所有分类,以及子分类，select多选联动
     * @return
     */
    public String getAllCategory(){
        List<Category> parentCategory = categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_category_id", "0"));
        List<Map<String,Object>> list = new ArrayList<>();
        parentCategory.stream().forEach(parent -> {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("name",parent.getCategoryName());
            map1.put("value",parent.getCategoryId());
            List<Object> list2 = new ArrayList<>();
            List<Category> childCategory = categoryMapper.selectList(new QueryWrapper<Category>().eq("parent_category_id", parent.getCategoryId()));
            childCategory.stream().forEach(child->{
                Map<String,Object> map2 = new HashMap<>();
                map2.put("name",child.getCategoryName());
                map2.put("value",child.getCategoryId());
                list2.add(map2);
            });
            map1.put("children",list2);
            list.add(map1);
        });
        return FastJsonUtils.convertObjectToJSON(list);
    }


    /**
     * 根据多个categoryId查询分类
     * @param categoryList
     * @return
     */
    public List<Category> getCategoryByCategoryIds(List<String> categoryList){
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",categoryList);
        return categoryMapper.selectList(queryWrapper);
    }
}
