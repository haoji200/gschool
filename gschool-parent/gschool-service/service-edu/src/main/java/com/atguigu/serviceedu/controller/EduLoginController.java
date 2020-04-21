package com.atguigu.serviceedu.controller;

import com.atguigu.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * EduLoginController
 *
 * @Author: wd
 * @CreateTime: 2020-04-10
 * @Description:
 */
@Api(description = "登录")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/login")
public class EduLoginController {

    @PostMapping("user/login")
    public R login(){
        System.out.println("1111");
        return R.ok().data("token","admin");
    }

    @GetMapping("user/info")
    public R info(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("roles","[admin]");
        hashMap.put("name","admin");
        hashMap.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.ok().data(hashMap);
    }
}