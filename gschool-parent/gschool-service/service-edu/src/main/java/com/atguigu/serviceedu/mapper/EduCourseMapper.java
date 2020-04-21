package com.atguigu.serviceedu.mapper;

import com.atguigu.serviceedu.entity.CoursePublishVo;
import com.atguigu.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo selectCourseConfirmInfoById(@Param("id") String id);
}
