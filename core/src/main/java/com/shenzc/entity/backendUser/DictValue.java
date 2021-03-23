package com.shenzc.entity.backendUser;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class DictValue {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String dictId;

    private String name;

    private Integer value;

    private String createBy;

    private Date createAt;

    private String updateBy;

    private Date updateAt;

}
