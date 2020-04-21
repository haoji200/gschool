package com.atguigu.serviceedu.controller;


import com.atguigu.common.R;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.teachervo.QueryTeacher;
import com.atguigu.serviceedu.handler.myException.MyException;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
@Api(description="讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "展示所有讲师列表")
    @GetMapping("getAll")
    public R getAll(){
        try {
        } catch (Exception e) {
            throw new MyException(10086,"执行了自定义异常处理");
        }
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);
        return R.ok().data("item",eduTeachers);
    }

    @ApiOperation(value = "根据单个ID删除讲师")
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "分页展示讲师列表")
    @GetMapping("showTeacherPage/{currentPage}/{size}")
    public R showTeacherPage(@PathVariable Long currentPage,@PathVariable Long size){
        Page<EduTeacher> page = new Page<>(currentPage,size);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        long pageSize = page.getSize();
        List<EduTeacher> records = page.getRecords();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total",total);
        hashMap.put("size",pageSize);
        hashMap.put("records",records);
        return R.ok().data(hashMap);
    }

    @ApiOperation(value = "根据条件分页展示讲师列表")
    @PostMapping("showTeacherPageByCondition/{currentPage}/{size}")
    public R showTeacherPageByCondition(@PathVariable Long currentPage,
                                        @PathVariable Long size,
                                        @RequestBody(required = false)QueryTeacher queryTeacher){
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String teacherName = queryTeacher.getName();
        Integer teacherLevel = queryTeacher.getLevel();
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();
        if(!StringUtils.isEmpty(teacherName)){
            queryWrapper.like("name",teacherName);
        }
        if(!StringUtils.isEmpty(teacherLevel)){
            queryWrapper.eq("level",teacherLevel);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        Page<EduTeacher> page = new Page<>(currentPage,size);
        eduTeacherService.page(page,queryWrapper);
        long total = page.getTotal();
        long pageSize = page.getSize();
        List<EduTeacher> records = page.getRecords();
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("total",total);
//        hashMap.put("size",pageSize);
//        hashMap.put("records",records);
        return R.ok().data("total",total).data("size",pageSize).data("records",records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.save(eduTeacher);
        return R.ok();
    }
    
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("findTeacherById/{id}")
    public R findTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    @ApiOperation(value = "根据ID更新讲师信息")
    @PostMapping("updateTeacherById")
    public R updateTeacherById(@RequestBody EduTeacher eduTeacher){
        eduTeacherService.updateById(eduTeacher);
        return R.ok();
    }
}

