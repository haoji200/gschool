package com.atguigu.serviceedu.controller;


import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Api(description="课程小节管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "添加小节信息")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        Integer count = eduVideoService.checkVideoByChapter(eduVideo);
        if(count == 0){
            eduVideoService.save(eduVideo);
            return R.ok();
        }
        return R.error().message("章节中已有该小节，无法重复添加");
    }

    @ApiOperation(value = "获取小节信息")
    @GetMapping("getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    @ApiOperation(value = "更新小节信息")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "删除小节信息")
    @DeleteMapping("deleteVideoById/{videoId}")
    public R deleteVideoById(@PathVariable String videoId){
        eduVideoService.removeVideoById(videoId);
        return R.ok();
    }

    @ApiOperation(value = "根据课程ID删除多个视频")
    @DeleteMapping("deleteManyVideoById")
    public R deleteManyVideoById(@RequestParam("courseIds") List<String> courseIds){
        eduVideoService.deleteManyVideoById(courseIds);
        return R.ok();
    }
}

