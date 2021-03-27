package com.tj.resolvedemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tj.resolvedemo.domain.JxlTable;
import com.tj.resolvedemo.util.ResolveMDBFileThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: resolve-demo
 * @Date: 2021/3/25 10:31
 * @Author: Mr.SU
 * @Description: 通用监听类
 */
public class StringExcelListener extends AnalysisEventListener {

    private static final Logger logger = LoggerFactory.getLogger(StringExcelListener.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
    private String serialNo = sdf.format(new Date());

    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private List<List<String>> datas = new ArrayList<>();
    private List<JxlTable> jxlTableList = new ArrayList<>();

    /**
     * 每解析一行都会回调invoke()方法
     *
     * @param object  读取后的数据对象
     * @param context 内容
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        @SuppressWarnings("unchecked") Map<Object, String> stringMap = (HashMap<Object, String>) object;
        JxlTable jxlTable = new JxlTable();
        jxlTable.setSerialNo(serialNo);
//        logger.info("stringMap:{}", stringMap);
        Iterator<Object> iterator = stringMap.keySet().iterator();
        while (iterator.hasNext()) {
            int index = (Integer)iterator.next();
            ResolveMDBFileThreadUtil.setFieldValueByFieldName(index, stringMap.get(index), jxlTable);
        }
        jxlTableList.add(jxlTable);

//        logger.info("jxlTableList:{}", jxlTableList);
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
//        datas.add(new ArrayList<>(stringMap.values()));
        //根据自己业务做处理
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        //注意不要调用datas.clear(),否则getDatas为null
    }

    /**
     * 返回数据
     *
     * @return 返回读取的数据集合
     **/
    public List<List<String>> getDatas() {
        return datas;
    }

    /**
     * 设置读取的数据集合
     *
     * @param datas 设置读取的数据集合
     **/
    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }

    public List<JxlTable> getJxlTableList() {
        return jxlTableList;
    }

    public void setJxlTableList(List<JxlTable> jxlTableList) {
        this.jxlTableList = jxlTableList;
    }
}