package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.entity.ChapterVo;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.entity.VideoVo;
import com.atguigu.serviceedu.handler.myException.MyException;
import com.atguigu.serviceedu.mapper.EduChapterMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getCVByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterWrapper);
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(videoWrapper);
        List<ChapterVo> resultList = new ArrayList<>();
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            resultList.add(chapterVo);
            List<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setVideoVos(videoVos);
        }

        return resultList;
    }

    @Override
    public List<EduChapter> checkChapterByTitle(EduChapter eduChapter) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("title",eduChapter.getTitle());
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);
        return eduChapters;
    }

    @Override
    public void deleteChapterById(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count>0){
            throw new MyException(20001,"章节中包含小节内容，无法删除");
        }
        baseMapper.deleteById(chapterId);
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        UpdateWrapper<EduChapter> wrapper = new UpdateWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
