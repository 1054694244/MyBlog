/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 17:45
 */
@Data
public class Menu {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String menuId;

    private String menuName;

    private String parentId;

    private String url;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;
}
