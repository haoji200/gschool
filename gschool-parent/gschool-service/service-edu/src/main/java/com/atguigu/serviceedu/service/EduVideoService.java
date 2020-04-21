package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
public interface EduVideoService extends IService<EduVideo> {


    Integer checkVideoByChapter(EduVideo eduVideo);

    void removeVideoByCourseId(String courseId);

    void deleteOneVideoById(String videoId);

    void deleteManyVideoById(List<String> videoIds);

    void removeVideoById(String videoId);
}
