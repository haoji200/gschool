package demo.excel;

import com.alibaba.excel.EasyExcel;

/**
 * readExcel
 *
 * @Author: wd
 * @CreateTime: 2020-04-12
 * @Description:
 */
public class readExcel {
    public static void main(String[] args) {
        String fileName = "D:\\1015.xlsx";
        EasyExcel.read(fileName,StuDataForRead.class,new ReadExcelListener()).sheet().doRead();
    }
}