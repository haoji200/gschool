package com.atguigu.serviceedu.client;

import com.atguigu.common.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * VodClientImpl
 *
 * @Author: wd
 * @CreateTime: 2020-04-19
 * @Description:
 */
@Component
public class VodClientImpl implements VodClient{
    @Override
    public R deleteOneVideoById(String videoId) {
        return R.error().message("调用了one熔断器方法");
    }

    @Override
    public R deleteManyVideos(List<String> videoIds) {
        return R.error().message("调用了many熔断器方法");
    }
}