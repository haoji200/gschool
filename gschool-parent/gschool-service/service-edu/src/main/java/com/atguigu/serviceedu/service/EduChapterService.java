package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.ChapterVo;
import com.atguigu.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
public interface EduChapterService extends IService<EduChapter> {


    List<ChapterVo> getCVByCourseId(String courseId);

    List<EduChapter> checkChapterByTitle(EduChapter eduChapter);

    void deleteChapterById(String chapterId);

    void removeChapterByCourseId(String courseId);
}
