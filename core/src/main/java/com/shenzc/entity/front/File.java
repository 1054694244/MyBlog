package com.shenzc.entity.front;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class File {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String fileId;

    private String fileName;

    private String categoryId;

    private String integral;

    private String fileDesc;

    private String isPublic;

    private Integer isDelete;

    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    private String updateBy;

    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;

}
