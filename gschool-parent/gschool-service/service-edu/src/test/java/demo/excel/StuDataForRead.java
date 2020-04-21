package demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * StuDataForRead
 *
 * @Author: wd
 * @CreateTime: 2020-04-12
 * @Description:
 */
@Data
public class StuDataForRead {

    @ExcelProperty(value = "学生编号",index = 0)
    private String stuNo;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String stuName;
}