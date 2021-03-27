package com.tj.resolvedemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.tj.resolvedemo.util.ResolveMDBFileThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: resolve-demo
 * @Date: 2021/3/25 13:37
 * @Author: Mr.SU
 * @Description:
 */
public class EneityExcelListener<T extends BaseRowModel> extends AnalysisEventListener<T> {
    private static final Logger logger = LoggerFactory.getLogger(EneityExcelListener.class);

    private final List<T> rows = new ArrayList<>();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
    private String serialNo = sdf.format(new Date());

    @Override
    public void invoke(T object, AnalysisContext context) {
//        logger.info("object:{}", object);
        ResolveMDBFileThreadUtil.setFieldValueBySerialNo(serialNo, object);
        rows.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        logger.info("read rows:{}", rows.size());
    }

    public List<T> getRows() {
        return rows;
    }
}
