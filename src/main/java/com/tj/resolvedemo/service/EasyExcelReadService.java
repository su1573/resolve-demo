package com.tj.resolvedemo.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.tj.resolvedemo.domain.JxlTable;
import com.tj.resolvedemo.listener.EneityExcelListener;
import com.tj.resolvedemo.listener.StringExcelListener;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: resolve-demo
 * @Date: 2021/3/25 10:34
 * @Author: Mr.SU
 * @Description: EasyExcel读取excel
 */
@Service
public class EasyExcelReadService {

    /**
     * @Description: 根据excel输入流，读取excel文件，不使用实体映射
     * @param: "[inputStream]"
     * @Return: java.util.List<java.util.List < java.lang.String>>
     * @Author: supenghui
     * @Date: 2021/3/25 10:36
     */
    public List<JxlTable> writeWithoutHead(InputStream inputStream) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, null, listener).headRowNumber(0).build();
        excelReader.read();
        List<JxlTable> datas = listener.getJxlTableList();
        excelReader.finish();
        return datas;
    }

    /**
     * 根据excel输入流，读取excel文件，不使用实体映射
     * 从Excel中读取文件，读取的文件是一个DTO类，该类必须继承BaseRowModel
     * 具体实例参考 ： MemberMarketDto.java
     * 参考：https://github.com/alibaba/easyexcel
     * 字符流必须支持标记，FileInputStream 不支持标记，可以使用BufferedInputStream 代替
     * BufferedInputStream bis = new BufferedInputStream(new FileInputStream(...));
     */
    public <T extends BaseRowModel> List<T> readExcel(final InputStream inputStream, final Class<? extends BaseRowModel> clazz) {
        if (null == inputStream) {
            throw new NullPointerException("the inputStream is null!");
        }
        EneityExcelListener<T> listener = new EneityExcelListener<>();

        ExcelReader reader = new ExcelReader(inputStream, valueOf(inputStream), null, listener);
//        reader.read(new com.alibaba.excel.metadata.Sheet(0, 7, clazz)); // 这里因为EasyExcel-1.1.1版本的bug，所以需要选用下面这个标记已经过期的版本

        ReadSheet readSheet = new ReadSheet();
        readSheet.setSheetNo(0);
        readSheet.setClazz(clazz);
        readSheet.setHeadRowNumber(7);
//        readSheet.setSheetName(sheet.getSheetName());
//        readSheet.setHead(sheet.getHead());

        reader.read(readSheet);

        return listener.getRows();
    }

    /**
     * 根据输入流，判断为xls还是xlsx，该方法原本存在于easyexcel 1.1.0 的ExcelTypeEnum中。
     */
    public static ExcelTypeEnum valueOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (FileMagic.OLE2.equals(fileMagic)) {
                return ExcelTypeEnum.XLS;
            }
            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }
            throw new IllegalArgumentException("excelTypeEnum can not null");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
