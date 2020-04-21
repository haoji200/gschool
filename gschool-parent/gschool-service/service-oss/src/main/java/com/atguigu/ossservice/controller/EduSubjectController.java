package com.atguigu.ossservice.controller;


import com.atguigu.common.R;
import com.atguigu.ossservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-11
 */
@RestController
@CrossOrigin
@RequestMapping("/ossservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("uploadFileByOSS")
    public R uploadFileByOSS(MultipartFile file){
        String url = eduSubjectService.uploadFileByOSS(file);
        return R.ok().data("url",url);
    }
}

