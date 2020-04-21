package com.atguigu.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.ossservice.entity.EduSubject;
import com.atguigu.ossservice.mapper.EduSubjectMapper;
import com.atguigu.ossservice.service.EduSubjectService;
import com.atguigu.ossservice.utils.ConstProperties;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-11
 */
@Service
public class EduSubjectServiceImpl implements EduSubjectService {

    @Override
    public String uploadFileByOSS(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstProperties.END_POINT;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ConstProperties.ACCESS_KEY_ID;
        String accessKeySecret = ConstProperties.ACCESS_KEY_SECRET;
        String bucketName = ConstProperties.BUCKET_NAME;
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String dataString = new DateTime().toString("yyyy/MM/dd");
            String filename = dataString + "/" + uuid + file.getOriginalFilename();
            InputStream is = file.getInputStream();
            // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
            String content = "Hello OSS";
            ossClient.putObject(bucketName, filename, is);
            // 关闭OSSClient。
            ossClient.shutdown();
            String url = "http://" + bucketName + "." + endpoint + "/" +filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
