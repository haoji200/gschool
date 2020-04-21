package com.atguigu.serviceedu.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * OneSubjectVo
 *
 * @Author: wd
 * @CreateTime: 2020-04-13
 * @Description:
 */
@Data
public class OneSubjectVo {

    private String id;

    private String title;

    private List<TwoSubjectVo> children = new ArrayList<>();
}