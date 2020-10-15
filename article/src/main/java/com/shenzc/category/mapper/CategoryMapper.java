/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/29 14:56
 */
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
