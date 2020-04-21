package com.atguigu.serviceedu.client;

import com.atguigu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * VodClient
 *
 * @Author: wd
 * @CreateTime: 2020-04-18
 * @Description:
 */
@Component
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {

    @DeleteMapping("eduvod/video/{videoId}")
    public R deleteOneVideoById(@PathVariable("videoId") String videoId);

    @DeleteMapping("eduvod/video/delete-batch")
    public R deleteManyVideos(@RequestParam List<String> videoIds);



}