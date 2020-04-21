package com.atguigu.video.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

	String uploadVideoAliyun(MultipartFile file);

    void deleteVideoById(String videoId);

    void deleteManyVideos(List<String> videoIds);
}