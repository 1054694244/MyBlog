/*
 * Copyright (C) 1997-2020 康成投资（中国）有限公司
 *
 * http://www.rt-mart.com
 *
 * 版权归本公司所有，不得私自使用、拷贝、修改、删除，否则视为侵权
 */
package com.shenzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.backendUser.User;
import com.shenzc.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/7 14:20
 */
@Mapper
@Repository
public interface SSOMapper extends BaseMapper<User> {

    List<UserVo> selectByUser(UserVo userVo);

    void updateUser(@Param("id") int id, @Param("loginCount") int loginCount);

    int selectRoleNum(String roleId);
}
