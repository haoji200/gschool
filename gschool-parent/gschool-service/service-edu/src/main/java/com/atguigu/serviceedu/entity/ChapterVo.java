package com.atguigu.serviceedu.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Chapter
 *
 * @Author: wd
 * @CreateTime: 2020-04-14
 * @Description:
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> videoVos = new ArrayList<>();
}