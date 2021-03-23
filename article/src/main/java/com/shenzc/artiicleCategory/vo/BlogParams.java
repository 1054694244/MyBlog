package com.shenzc.artiicleCategory.vo;

import lombok.Data;

@Data
public class BlogParams {

    private Integer index;

    private String year;

    private Integer month;

    private Integer type;

    private String keyword;

    private Integer page;

    private Integer limit;

}
