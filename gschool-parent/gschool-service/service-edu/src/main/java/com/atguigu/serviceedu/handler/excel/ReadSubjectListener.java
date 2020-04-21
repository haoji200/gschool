package com.atguigu.serviceedu.handler.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.ExcelSubjectRead;
import com.atguigu.serviceedu.handler.myException.MyException;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * ReadSubjectListener
 *
 * @Author: wd
 * @CreateTime: 2020-04-13
 * @Description:
 */
public class ReadSubjectListener extends AnalysisEventListener<ExcelSubjectRead> {

    private EduSubjectService eduSubjectService;

    public ReadSubjectListener(){

    }
    public ReadSubjectListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubjectRead excelSubjectRead, AnalysisContext analysisContext) {
        if(excelSubjectRead==null){
            throw new MyException(20001,"读取失败");
        }
        String oneSubjectName = excelSubjectRead.getOneSubjectName();
        EduSubject oneSubject = this.isExistsOneSubject(oneSubjectName,eduSubjectService);
        if(oneSubject==null){
            oneSubject = new EduSubject();
            oneSubject.setTitle(oneSubjectName);
            oneSubject.setParentId("0");
            eduSubjectService.save(oneSubject);
        }
        String oneSubjectId = oneSubject.getId();
        String twoSubjectName = excelSubjectRead.getTwoSubjectName();
        EduSubject twoSuject = this.isExistsTwoSubject(twoSubjectName,oneSubjectId,eduSubjectService);
        if(twoSuject==null){
            twoSuject = new EduSubject();
            twoSuject.setTitle(twoSubjectName);
            twoSuject.setParentId(oneSubjectId);
            eduSubjectService.save(twoSuject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject isExistsOneSubject(String oneSubjectName,EduSubjectService eduSubjectService){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",oneSubjectName);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }

    private EduSubject isExistsTwoSubject(String twoSubjectName,String oneSubjectName,EduSubjectService eduSubjectService){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",twoSubjectName);
        wrapper.eq("parent_id",oneSubjectName);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
}