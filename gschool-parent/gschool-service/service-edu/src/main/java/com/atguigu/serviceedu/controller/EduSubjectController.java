package com.atguigu.serviceedu.controller;


import com.alibaba.excel.EasyExcel;
import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.ExcelSubjectRead;
import com.atguigu.serviceedu.entity.OneSubjectVo;
import com.atguigu.serviceedu.handler.excel.ReadSubjectListener;
import com.atguigu.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-12
 */
@Api(description="课程分类管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "读取上传表格数据")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        try {
            InputStream is = file.getInputStream();
            EasyExcel.read(is, ExcelSubjectRead.class,new ReadSubjectListener(eduSubjectService)).sheet().doRead();
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @ApiOperation(value = "读取课程分类列表数据")
    @GetMapping("showAllSubject")
    public R showAllSubject(){
        List<OneSubjectVo> oneSubjectVos = eduSubjectService.showAllSubject();
        return R.ok().data("allSubject",oneSubjectVos);
    }
}

