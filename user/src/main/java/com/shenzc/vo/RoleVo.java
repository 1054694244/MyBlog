/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.vo;

import com.shenzc.entity.Role;
import lombok.Data;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/24 9:34
 */
@Data
public class RoleVo extends Role {

    private Integer page;

    private Integer limit;

}
