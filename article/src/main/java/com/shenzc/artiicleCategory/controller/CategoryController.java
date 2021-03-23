/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.artiicleCategory.controller;

import com.shenzc.artiicleCategory.service.CategoryService;
import com.shenzc.resutl.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 14:55
 */
@RestController
@RequestMapping("/article/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getParentCategory")
    public ResultBody getParentCategory(){
        return ResultBody.success(categoryService.getParentCategory());
    }

    //大分类下的二级分类
    @GetMapping("/getChildCategory")
    public String getChildCategory(@RequestParam(value = "keyword")String categoryId){
        return categoryService.getChildCategory(categoryId);
    }

    @RequestMapping("/getAllCategory")
    public String getAllCategory(){
        return categoryService.getAllCategory();
    }

}
