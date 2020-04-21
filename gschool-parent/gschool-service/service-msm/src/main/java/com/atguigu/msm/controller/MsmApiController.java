package com.atguigu.msm.controller;

import com.atguigu.common.R;
import com.atguigu.msm.service.MsmApiService;
import com.atguigu.serviceutils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * MsmApiController
 *
 * @Author: wd
 * @CreateTime: 2020-04-20
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("edumsm/msm")
public class MsmApiController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MsmApiService msmApiService;

    @GetMapping("send/{phone}")
    public R sendCode(@PathVariable String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        boolean b = msmApiService.sendCode(phone,code);
        if(b){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error().message("发送验证码失败");
        }
    }

}