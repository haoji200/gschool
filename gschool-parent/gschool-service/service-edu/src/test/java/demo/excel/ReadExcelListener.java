package demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * ReadExcelListener
 *
 * @Author: wd
 * @CreateTime: 2020-04-12
 * @Description:
 */
public class ReadExcelListener extends AnalysisEventListener<StuDataForRead> {
    @Override
    public void invoke(StuDataForRead stuDataForRead, AnalysisContext analysisContext) {
        System.out.println("表内数据~~~"+stuDataForRead);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头数据~~~"+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("监听结束");
    }
}