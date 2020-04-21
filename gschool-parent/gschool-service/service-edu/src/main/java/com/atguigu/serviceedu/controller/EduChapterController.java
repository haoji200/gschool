package com.atguigu.serviceedu.controller;


import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.ChapterVo;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Api(description="课程章节管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "获取所有章节小节信息")
    @GetMapping("getCVByCourseId/{courseId}")
    public R getCVByCourseId(@PathVariable String courseId){
        List<ChapterVo> chapterVos = eduChapterService.getCVByCourseId(courseId);
        return R.ok().data("chaperVideo",chapterVos);
    }

    @ApiOperation(value = "添加章节信息")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        List<EduChapter> eduChapters = eduChapterService.checkChapterByTitle(eduChapter);
        if(eduChapters.size()==0){
            eduChapterService.save(eduChapter);
            return R.ok();
        }
        return R.error().message("课程中已有该章节，无法重复添加");
    }

    @ApiOperation(value = "获取章节信息")
    @GetMapping("getChapterById/{chapterId}")
    public R getChapterById(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }

    @ApiOperation(value = "更新章节信息")
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "删除章节信息")
    @DeleteMapping("deleteChapterById/{chapterId}")
    public R deleteChapterById(@PathVariable String chapterId){
        eduChapterService.deleteChapterById(chapterId);
        return R.ok();
    }
}

