package demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * StuData
 *
 * @Author: wd
 * @CreateTime: 2020-04-12
 * @Description:
 */
@Data
public class StuData {

    @ExcelProperty("学生编号")
    private String stuNo;

    @ExcelProperty("学生姓名")
    private String stuName;
}