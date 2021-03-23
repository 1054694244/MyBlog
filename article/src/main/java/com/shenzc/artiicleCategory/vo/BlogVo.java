package com.shenzc.artiicleCategory.vo;

import com.shenzc.entity.front.Blog;
import lombok.Data;

@Data
public class BlogVo extends Blog {

    private Integer page;

    private Integer limit;

}
