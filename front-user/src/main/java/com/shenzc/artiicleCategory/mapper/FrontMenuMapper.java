package com.shenzc.artiicleCategory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.entity.front.FrontMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FrontMenuMapper extends BaseMapper<FrontMenu> {
}
