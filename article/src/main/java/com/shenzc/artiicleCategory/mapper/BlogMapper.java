package com.shenzc.artiicleCategory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzc.artiicleCategory.vo.BlogVo;
import com.shenzc.entity.front.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {

    List<BlogVo> selectBlog(Map<String,Object> paramMap);

}
