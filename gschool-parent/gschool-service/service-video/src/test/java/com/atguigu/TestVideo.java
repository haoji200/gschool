package com.atguigu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

import static com.atguigu.InitVodObject.initVodClient;


/**
 * TestVideo
 *
 * @Author: wd
 * @CreateTime: 2020-04-17
 * @Description:
 */
public class TestVideo {


    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("a3e5ec48b9694ef1ba80ce9aa7c5c1e0");
        return client.getAcsResponse(request);
    }
    /*以下为调用示例*/
    public static void main(String[] argv) {
        DefaultAcsClient client = null;
        try {
            client = initVodClient("LTAI4FmbyacASjjMRJ658E3Z", "woYAqk0ML3wRJ3GaTbdtCl34Fczkbi");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
        /*public static void main(String[] args) {
        //        //获取视频播放凭证
        //        //1 获取初始化对象
        //        try {
        //            DefaultAcsClient client = InitVodObject.initVodClient("LTAI4Fjj22b42ZWDT6pBCCEK", "K4U9wG33N8nwVKJNE9seW8jQCwV7u2");
        //
        //            //2 创建获取视频凭证request和response对象
        //            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        //            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //
        //            //3 向request设置视频id
        //            request.setVideoId("3c64fe7e537d4c6295c69c8b154803a8");
        //
        //            //4 调用初始化对象里面的方法
        //            response = client.getAcsResponse(request);
        //
        //            //5 通过response得到调用返回结果
        //            String playAuth = response.getPlayAuth();
        //            System.out.println("playAuth: "+playAuth);
        //
        //        } catch (ClientException e) {
        //            e.printStackTrace();
        //        }

            try {
                getPlayUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    //1 根据视频iD获取视频播放地址
    public static void getPlayUrl() throws Exception{
        //创建初始化对象
        DefaultAcsClient client = InitVodObject.initVodClient("LTAI4Fjj22b42ZWDT6pBCCEK", "K4U9wG33N8nwVKJNE9seW8jQCwV7u2");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("a3e5ec48b9694ef1ba80ce9aa7c5c1e0");
        //调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }*/
}