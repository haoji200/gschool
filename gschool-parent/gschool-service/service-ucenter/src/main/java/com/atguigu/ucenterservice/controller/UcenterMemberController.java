package com.atguigu.ucenterservice.controller;


import com.atguigu.common.JwtUtils;
import com.atguigu.common.R;
import com.atguigu.ucenterservice.entity.RegisterVo;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author wd
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/ucenterservice/ucenter-member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember){
        String token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token",token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("getUserInfoToken")
    public R getUserInfoToken(HttpServletRequest request){
        Short s = 1;
        s = (short)(s+1);
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(id);
        return R.ok().data("member",member);
    }
}

