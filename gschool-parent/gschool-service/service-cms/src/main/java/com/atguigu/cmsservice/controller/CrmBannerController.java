package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.common.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author wd
 * @since 2020-04-19
 */
@Api(description = "后台banner管理")
@RestController
@CrossOrigin
@RequestMapping("/cmsservice/bannerAdmin")
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("分页展示")
    @GetMapping("{currentPage}/{limit}")
    public R getPageList(@PathVariable long currentPage,@PathVariable long limit){
        Page<CrmBanner> page = new Page<>(currentPage,limit);
        crmBannerService.page(page,null);
        return R.ok().data("total",page.getTotal()).data("rows",page.getRecords());
    }

    @ApiOperation("查询banner")
    @GetMapping("get/{id}")
    public R getBannerById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return R.ok().data("banner",crmBanner);
    }

    @ApiOperation("增加banner")
    @PostMapping("add")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation("删除banner")
    @DeleteMapping("remove/{id}")
    public R removeBannerById(@PathVariable String id){
        crmBannerService.removeById(id);
        return R.ok();
    }

    @ApiOperation("修改banner")
    @PutMapping("update")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }
}

