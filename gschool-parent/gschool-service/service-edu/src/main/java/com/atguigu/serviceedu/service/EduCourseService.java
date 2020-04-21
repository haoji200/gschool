package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.CourseInfoForm;
import com.atguigu.serviceedu.entity.CoursePublishVo;
import com.atguigu.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String courseId);

    void updateCourse(CourseInfoForm courseInfoForm);

    CoursePublishVo getCourseConfirmInfoById(String courseId);

    IPage<Map<String, Object>> publishCourseList();

    void deleteCourseById(String courseId);

    List<EduCourse> getHotCourse();
}
