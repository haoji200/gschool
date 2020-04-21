package com.atguigu.serviceedu.controller;

import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IndexController
 *
 * @Author: wd
 * @CreateTime: 2020-04-19
 * @Description:
 */
@Api(description="前台首页展示")
@RestController
@CrossOrigin
@RequestMapping("eduservice")
public class IndexController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public R index(){
        List<EduCourse> eduCourses = eduCourseService.getHotCourse();
        List<EduTeacher> eduTeachers = eduTeacherService.getHotTeacher();
        return R.ok().data("courseList",eduCourses).data("teacherList",eduTeachers);
    }
}