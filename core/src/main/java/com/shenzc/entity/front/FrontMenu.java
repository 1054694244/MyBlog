package com.shenzc.entity.front;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class FrontMenu {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String menuId;

    private String menuName;

    private String parentMenuId;

    private String url;

    private String createBy;

    private Date createAt;

    private String updateBy;

    private Date updateAt;

}
