package demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * WriteExcel
 *
 * @Author: wd
 * @CreateTime: 2020-04-12
 * @Description:
 */
public class WriteExcel {
    public static void main(String[] args) {
        String fileName = "D:\\1015.xlsx";
        EasyExcel.write(fileName,StuData.class).sheet("写操作测试").doWrite(createData());
    }
    public static List<StuData> createData(){
        List<StuData> stuDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StuData stuData = new StuData();
            stuData.setStuNo(i+"");
            stuData.setStuName("大佬"+i);
            stuDataList.add(stuData);
        }
        return stuDataList;
    }
}