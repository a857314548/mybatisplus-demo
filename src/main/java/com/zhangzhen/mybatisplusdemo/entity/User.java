package com.zhangzhen.mybatisplusdemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    //@TableId(type = IdType.AUTO)自增
    //@TableId(type = IdType.ID_WORKER) 用于long
    //@TableId(type = IdType.ID_WORKER_STR) 用于字符串
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
