/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.category.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shenzc.category.mapper.CategoryMapper;
import com.shenzc.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 查询所有分类
     * @return
     */
    public List<Category> getAllCategory(){
        return categoryMapper.selectList(new QueryWrapper<Category>());
    }
}
