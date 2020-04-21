package com.atguigu.video.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.video.Exception.MyException;
import com.atguigu.video.entity.ConstantPropertiesUtil;
import com.atguigu.video.service.VideoService;
import com.atguigu.video.utils.AliyunVodSDKUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

	@Override
	public String uploadVideoAliyun(MultipartFile file) {

		try {
			//title      01
			//fileName   01.00.009.mp4
			//inputStream
			String fileName = file.getOriginalFilename();
			String title = fileName.substring(0,fileName.lastIndexOf("."));
			InputStream inputStream = file.getInputStream();
			UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID,
					ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);

			UploadVideoImpl uploader = new UploadVideoImpl();
			UploadStreamResponse response = uploader.uploadStream(request);
			String videoId = null;
			if (response.isSuccess()) {
				videoId = response.getVideoId();
			} else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
				videoId = response.getVideoId();
			}
			return videoId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteVideoById(String videoId) {
		try {
			DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
			DeleteVideoRequest request = new DeleteVideoRequest();
			request.setVideoIds(videoId);
			client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteManyVideos(List<String> videoIds) {
		try {
			DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
			DeleteVideoRequest request = new DeleteVideoRequest();
			String ids = StringUtils.join(videoIds.toArray(), ",");
			request.setVideoIds(ids);
			client.getAcsResponse(request);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}