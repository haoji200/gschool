package com.atguigu.video.controller;

import com.atguigu.common.R;
import com.atguigu.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * UploadVideo
 *
 * @Author: wd
 * @CreateTime: 2020-04-18
 * @Description:
 */
@Api(description = "视频管理")
@RestController
@CrossOrigin
@RequestMapping("eduvod/video")
public class VodController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("uploadVideoAliyun")
    public R uploadVideoAliyun(MultipartFile file){
        String videoId = videoService.uploadVideoAliyun(file);
        return R.ok().data("videoId",videoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("{videoId}")
    public R deleteOneVideoById(@PathVariable String videoId){
        videoService.deleteVideoById(videoId);
        return R.ok();
    }

    @ApiOperation("删除多个视频")
    @DeleteMapping("delete-batch")
    public R deleteManyVideos(@RequestParam List<String> videoIds){
        videoService.deleteManyVideos(videoIds);
        return R.ok();
    }

}