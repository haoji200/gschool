package com.atguigu.serviceedu.service.impl;

import com.atguigu.common.R;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.handler.myException.MyException;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-13
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public Integer checkVideoByChapter(EduVideo eduVideo) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("title",eduVideo.getTitle());
        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> videoQueryWrapperwrapper = new QueryWrapper<>();
        videoQueryWrapperwrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = baseMapper.selectList(videoQueryWrapperwrapper);
        List<String> ids = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                ids.add(videoSourceId);
            }
        }
        if(ids.size()>0){
            R r = vodClient.deleteManyVideos(ids);
            if(!r.getSuccess()){
                throw new MyException(20001,r.getMessage());
            }
        }
        UpdateWrapper<EduVideo> wrapper = new UpdateWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void deleteOneVideoById(String videoId) {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
            vodClient.deleteOneVideoById(videoId);
        }
        int i = baseMapper.deleteById(videoId);
        if(i==0){
            throw new MyException(20001,"删除小节失败");
        }
    }

    @Override
    public void deleteManyVideoById(List<String> videoIds) {
        List<EduVideo> eduVideos = baseMapper.selectBatchIds(videoIds);
        for (EduVideo eduVideo : eduVideos) {
            if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
                R r = vodClient.deleteManyVideos(videoIds);
                if(!r.getSuccess()){
                    throw new MyException(20001,"debug熔断器");
                }
            }
        }
    }

    @Override
    public void removeVideoById(String videoId) {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
            vodClient.deleteOneVideoById(videoId);
        }
        int i = baseMapper.deleteById(videoId);
        if(i==0){
            throw new MyException(20001,"删除小节失败");
        }
    }
}
