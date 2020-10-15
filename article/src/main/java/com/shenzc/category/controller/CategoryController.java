/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.category.controller;

import com.shenzc.category.service.CategoryService;
import com.shenzc.resutl.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 14:55
 */
@RestController
@RequestMapping("/front/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategory")
    public ResultBody getAllCategory(){
        return ResultBody.success(categoryService.getAllCategory());
    }

}
