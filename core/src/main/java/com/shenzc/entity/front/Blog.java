package com.shenzc.entity.front;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shenzc.anno.excel.ExcelAnno;
import lombok.Data;

@Data
public class Blog {

    @TableId(type = IdType.AUTO)
    private String id;

    @ExcelAnno(title = "博客ID",column = 3)
    private String blogId;

    private String title;

    @ExcelAnno(title = "内容",column = 1)
    private String content;

    private String categoryIds;

    /**
     * 文章类型：1 原创，2 转载 3 翻译
     */
    private Integer type;

    /**
     * 审核状态
     */
    private Integer status;

    /**
     * 1：公共的， 0 ：私有的
     */
    private Integer isPublic;

    /**
     * 0：未删除 1：已删除
     */
    @ExcelAnno(title = "是否删除",column = 2)
    private Integer isDelete;

    private String createBy;

    private String createAt;

    private String updateBy;

    private String updateAt;


}
