/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.entity.backendUser;

import com.shenzc.anno.valid.MyValid;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 15:11
 */
@Data
public class Category {

    @MyValid
    private Integer id;

    @MyValid
    private String categoryId;

    private String categoryName;

    private String parentCategoryId;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

}
