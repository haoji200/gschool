package com.atguigu.serviceedu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * ExcelSubjectRead
 *
 * @Author: wd
 * @CreateTime: 2020-04-13
 * @Description:
 */
@Data
public class ExcelSubjectRead {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;

}