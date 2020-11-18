package com.len.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: ExcelListener
 * @Description: Excel 工具监听器
 * @author:by yangtianfeng
 * @classDate: 2020/8/31 14:15
 * @Version: 1.0
 **/
public class ExcelListener extends AnalysisEventListener {
    /**
     * 自定义用于暂时存储data。
     * 可以通过实例获取该值
     */
    private List<Object> datas = new ArrayList<>();


    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(o);
        //根据业务自行 do something
        /*
        如数据过大，可以进行定量分批处理
        if(datas.size()<=100){
            datas.add(object);
        }else {
            doSomething();
            datas = new ArrayList<Object>();
        }
         */
    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }


    /**
     * @return void
     * @Author YangTianFeng
     * @Description  解析结束销毁不用的资源
     * @Date 14:43 2020/8/31
     * @Param [analysisContext]
     **/
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //datas.clear();
    }


    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
