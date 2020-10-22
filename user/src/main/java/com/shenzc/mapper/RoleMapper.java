package com.shenzc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.backendUser.Role;
import com.shenzc.vo.RoleVo;
import com.shenzc.vo.TreeVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author Shenzc
 * @Date 2020/9/8 16:01
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleVo> selectAllRole(String roleName);

    List<TreeVo> getTreeVoList();

    List<TreeVo> getChlidTreeList(String menuId);

    List<String> getSelectTreeId(String roleId);
}
