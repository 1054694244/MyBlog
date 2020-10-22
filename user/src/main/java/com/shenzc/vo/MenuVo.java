/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.vo;

import com.shenzc.entity.backendUser.Menu;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/9 15:57
 */
@Data
public class MenuVo extends Menu {

    private String parentMenuName;

    private List<Menu> menuList;

    private Integer page;

    private Integer limit;

}
