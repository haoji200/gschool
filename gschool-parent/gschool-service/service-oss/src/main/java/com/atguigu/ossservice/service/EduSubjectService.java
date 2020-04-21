package com.atguigu.ossservice.service;

import com.atguigu.ossservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-11
 */
public interface EduSubjectService {

    String uploadFileByOSS(MultipartFile file);
}
