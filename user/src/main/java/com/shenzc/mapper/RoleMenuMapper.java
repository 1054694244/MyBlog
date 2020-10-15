package com.shenzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.RoleMenu;
import com.shenzc.vo.TreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/8 16:01
 */
@Mapper
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    void batchInsertRoleMenu(@Param("roleId") String roleId, @Param("list") List<TreeVo> list);
    void deleteByRoleId(String roleId);
}
