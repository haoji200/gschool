package com.atguigu.serviceedu.controller;


import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.CourseInfoForm;
import com.atguigu.serviceedu.entity.CoursePublishVo;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Api(description="课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = eduCourseService.addCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据id查询课程信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo",courseInfoForm);
    }

    @ApiOperation(value = "更新课程信息")
    @PostMapping("updateCourse")
    public R updateCourse(@RequestBody CourseInfoForm courseInfoForm){
        eduCourseService.updateCourse(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "课程信息确认")
    @GetMapping("getCourseConfirmInfoById/{courseId}")
    public R getCourseConfirmInfoById(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getCourseConfirmInfoById(courseId);
        return R.ok().data("confirmInfo",coursePublishVo);
    }

    @ApiOperation(value = "课程最终发布")
    @PutMapping("publishCourseById/{id}")
    public R publishCourseById(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation(value = "全部课程的分页查询")
    @GetMapping("publishCourseList")
    public R publishCourseList(){
        IPage<Map<String, Object>> mapIPage = eduCourseService.publishCourseList();
        return R.ok().data("items",mapIPage);
    }

    @ApiOperation(value = "根据Id删除课程")
    @DeleteMapping("deleteCourseById/{courseId}")
    public R deleteCourseById(@PathVariable String courseId){
        eduCourseService.deleteCourseById(courseId);
        return R.ok();
    }
}

