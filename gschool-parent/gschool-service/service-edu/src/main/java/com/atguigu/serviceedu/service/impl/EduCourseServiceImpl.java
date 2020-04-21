package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.*;
import com.atguigu.serviceedu.handler.myException.MyException;
import com.atguigu.serviceedu.mapper.EduCourseMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Transactional
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private VodClient vodClient;

    @Override
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert==0){
            throw new MyException(20001,"添加课程失败");
        }
        String courseId = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return courseId;
    }

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        if(eduCourse == null){
            throw new MyException(20001,"数据不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        if(eduCourseDescription!=null){
            courseInfoForm.setDescription(eduCourseDescription.getDescription());
        }
        return courseInfoForm;
    }

    @Override
    public void updateCourse(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i == 0){
            throw new MyException(20001,"修改课程失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm,eduCourseDescription);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getCourseConfirmInfoById(String courseId) {
        CoursePublishVo coursePublishVo = eduCourseMapper.selectCourseConfirmInfoById(courseId);
        return coursePublishVo;
    }

    @Override
    public IPage<Map<String, Object>> publishCourseList() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        Page<EduCourse> page = new Page<>(1,40);
        IPage<Map<String, Object>> mapIPage = baseMapper.selectMapsPage(page, null);
        return mapIPage;
    }

    @Override
    public void deleteCourseById(String courseId) {

        //根据课程Id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程Id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程Id删除描述
        eduCourseDescriptionService.removeById(courseId);
        //根据课程Id删除课程
        int i = baseMapper.deleteById(courseId);
        if(i==0){
            throw new MyException(20001,"删除课程失败");
        }
    }

    @Override
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");
        List<EduCourse> eduCourses = baseMapper.selectList(wrapper);
        return eduCourses;
    }
}
