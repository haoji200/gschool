package com.atguigu.cmsservice.controller;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.common.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CrmBannerFrontController
 *
 * @Author: wd
 * @CreateTime: 2020-04-19
 * @Description:
 */
@Api(description = "前台banner管理")
@RestController
@CrossOrigin
@RequestMapping("/cmsservice/bannerFront")
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBannerList")
    public R getAllBannerList(){
        List<CrmBanner> crmBanners = crmBannerService.getAllBannerList();
        return R.ok().data("bannerList",crmBanners);
    }
}