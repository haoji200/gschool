package com.atguigu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

/**
 * MyUser
 *
 * @Author: wd
 * @CreateTime: 2020-04-03
 * @Description:
 */
@Data
public class MyUser {

    @TableId(type = IdType.ID_WORKER)
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

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}