package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.OneSubjectVo;
import com.atguigu.serviceedu.entity.TwoSubjectVo;
import com.atguigu.serviceedu.mapper.EduSubjectMapper;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<OneSubjectVo> showAllSubject() {
        QueryWrapper<EduSubject> oneSubjectQueryWrapper = new QueryWrapper<>();
        oneSubjectQueryWrapper.eq("parent_id","0");
        List<EduSubject> oneSubjects = baseMapper.selectList(oneSubjectQueryWrapper);
        QueryWrapper<EduSubject> twoSubjectQueryWrapper = new QueryWrapper<>();
        twoSubjectQueryWrapper.ne("parent_id","0");
        List<EduSubject> twoSubjects = baseMapper.selectList(twoSubjectQueryWrapper);
        List<OneSubjectVo> resultList = new ArrayList<>();
        for (EduSubject oneSubject : oneSubjects) {
            OneSubjectVo oneSubjectVo = new OneSubjectVo();
            BeanUtils.copyProperties(oneSubject,oneSubjectVo);
            resultList.add(oneSubjectVo);
            List<TwoSubjectVo> twoSubjectVos = new ArrayList<>();
            for (EduSubject twoSubject : twoSubjects) {
                if(twoSubject.getParentId().equals(oneSubject.getId())){
                    TwoSubjectVo twoSubjectVo = new TwoSubjectVo();
                    BeanUtils.copyProperties(twoSubject,twoSubjectVo);
                    twoSubjectVos.add(twoSubjectVo);
                }
            }
            oneSubjectVo.setChildren(twoSubjectVos);
        }
        return resultList;
    }
}
