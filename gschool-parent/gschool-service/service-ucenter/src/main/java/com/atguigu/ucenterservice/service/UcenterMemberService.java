package com.atguigu.ucenterservice.service;

import com.atguigu.ucenterservice.entity.RegisterVo;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author wd
 * @since 2020-04-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);
}
