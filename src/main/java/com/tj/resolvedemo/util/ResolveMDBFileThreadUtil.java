package com.tj.resolvedemo.util;

import com.tj.resolvedemo.domain.Gzb;
import com.tj.resolvedemo.domain.JxlTable;
import com.tj.resolvedemo.domain.JxlTableEntity;
import com.tj.resolvedemo.mapper.GzbInfoMapper;
import com.tj.resolvedemo.mapper.JxlTableInfoMapper;
import com.tj.resolvedemo.service.EasyExcelReadService;
import com.tj.resolvedemo.test.ResolveFile;
import edu.npu.fastexcel.FastExcel;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: driving-agent
 * @Date: 2021/2/4 10:40
 * @Author: Mr.SU
 * @Description:
 */
@Service("resolveMDBFileThreadUtil")
public class ResolveMDBFileThreadUtil {
    protected Logger logger = LoggerFactory.getLogger(ResolveMDBFileThreadUtil.class);

    @Autowired
    private GzbInfoMapper gzbInfoMapper;

    @Autowired
    private JxlTableInfoMapper jxlTableInfoMapper;

    @Autowired
    private EasyExcelReadService easyExcelReadService;

    /**
     * @Description: MDB文件线程处理派单, 加锁
     * @param: "[orderId, openId]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/2/4 13:53
     */
    public void dealOrderToDriver(Integer start, Integer end, String path, String ext) {
        ResolveFile rmf = new ResolveFile();
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<Gzb> gzbList = rmf.readFileSingalTableACCESS(newpaths); //读取单表
                map.put("gzbInfos", gzbList);
                gzbInfoMapper.startInsertGzbConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }


    /**
     * @Description: 使用JXL，处理解析xls文件
     * @param: "[start, end, path, ext]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/23 18:57
     */
    public void dealJxlXlsFile(Integer start, Integer end, String path, String ext) {
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<JxlTable> jxlTables = readRowsAndColums(newpaths); //读取行列的值
                map.put("jxlTables", jxlTables);
                jxlTableInfoMapper.startInsertJxlConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }

    /**
     * @Description: 读取行列的值
     * @param: "[file]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/23 16:05
     */
    private List<JxlTable> readRowsAndColums(File file) throws BiffException, IOException {

        //1:创建workbook
        Workbook workbook = Workbook.getWorkbook(file);
        //2:获取第一个工作表sheet
        Sheet sheet = workbook.getSheet(0);
        //3:获取数据
//        System.out.println("行："+sheet.getRows());
//        System.out.println("列："+sheet.getColumns());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
        String serialNo = sdf.format(new Date());
        List<JxlTable> jxlTables = new ArrayList<>();
//        Cell cell0=sheet.getCell(1,4);
//        Cell cellV=sheet.getCell(6,7);
//        System.out.println(cell0.getContents());
//        System.out.println(cellV.getContents());
        for (int i = 7; i < sheet.getRows(); i++) {
            JxlTable jxlTable = new JxlTable();
            jxlTable.setSerialNo(serialNo);
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                String value = cell.getContents();
                setFieldValueByFieldName(j, value, jxlTable);
            }
            jxlTables.add(jxlTable);
        }
        //最后一步：关闭资源
        workbook.close();
        return jxlTables;
    }

    public static void setFieldValueByFieldName(int fieldName, Object value, Object object) {
        try {
            Field field = object.getClass().getDeclaredField("cloum" + fieldName);
            field.setAccessible(true); //开通set权限
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 设置serialNo值
     * @param: "[value, object]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/25 13:56
     */
    public static void setFieldValueBySerialNo(Object value, Object object) {
        try {
            Field field = object.getClass().getDeclaredField("serialNo");
            field.setAccessible(true); //开通set权限
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 使用POI处理解析xls文件
     * @param: "[start, end, path, ext]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/24 10:00
     */
    public void dealPoiXlsFile(Integer start, Integer end, String path, String ext) {
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<JxlTable> jxlTables = resolveXlsFile(newpaths); //读取行列的值
                map.put("jxlTables", jxlTables);
                jxlTableInfoMapper.startInsertJxlConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }

    /**
     * @Description: poi解析文件
     * @param: "[file]"
     * @Return: java.util.List<com.tj.resolvedemo.domain.JxlTable>
     * @Author: supenghui
     * @Date: 2021/3/24 18:54
     */
    private List<JxlTable> resolveXlsFile(File file) throws BiffException, IOException {
        List<JxlTable> jxlTables = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
        String serialNo = sdf.format(new Date());

        int sheetNum = 0;
        int excelCounter = 0;// Excel计数器
        InputStream is = new FileInputStream(file);
        org.apache.poi.ss.usermodel.Workbook workbook = new HSSFWorkbook(is);
        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        int minRowIx = 7;
        int maxRowIx = sheet.getLastRowNum();
        short peviousMaxColIx = 0;// 上一行最大单元格格式
        try {
            //解析文件
            for (int rowIx = minRowIx; rowIx < maxRowIx; rowIx++) {
                Row row = sheet.getRow(rowIx);
                if (null != row) {
                    short minColIx = row.getFirstCellNum();
                    short maxColIx = row.getLastCellNum();
                    if (maxColIx > peviousMaxColIx) {
                        peviousMaxColIx = maxColIx;
                    } else {
                        maxColIx = peviousMaxColIx;
                    }
                    JxlTable jxlTable = new JxlTable();
                    jxlTable.setSerialNo(serialNo);
                    for (int colIx = minColIx; colIx < maxColIx; colIx++) {
                        org.apache.poi.ss.usermodel.Cell cell = row.getCell(new Integer(colIx));
                        String cellTest = String.valueOf(cell);
                        if ("".equals(cellTest) || null == cell) {
                            setFieldValueByFieldName(colIx, "", jxlTable);
                        } else {
                            CellValue cellValue = evaluator.evaluate(cell);
                            int num = cellValue.getCellType();
                            // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                            // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                            switch (num) {
                                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                                    setFieldValueByFieldName(colIx, cellValue.toString().trim(), jxlTable);
                                    break;
                                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        setFieldValueByFieldName(colIx, cell.getDateCellValue().toString().trim(), jxlTable);
                                    } else {
                                        setFieldValueByFieldName(colIx, String.valueOf(cell.getNumericCellValue()).trim(), jxlTable);
                                    }
                                    break;
                                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                                    setFieldValueByFieldName(colIx, cellValue.getStringValue().trim(), jxlTable);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    jxlTables.add(jxlTable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jxlTables;
    }

    /**
     * @Description: 使用EasyExcel处理解析xls文件 不使用实体类映射
     * @param: "[start, end, path, ext]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/25 10:43
     */
    public void dealEasyExcelXlsFile(Integer start, Integer end, String path, String ext) {
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<JxlTable> jxlTables = new ArrayList<>();
                try {
                    jxlTables = easyExcelReadService.writeWithoutHead(new FileInputStream(newpaths));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                map.put("jxlTables", jxlTables);
                jxlTableInfoMapper.startInsertJxlConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }


    /**
     * @Description: 使用EasyExcel处理解析xls文件 使用实体类映射
     * @param: "[start, end, path, ext]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/25 10:43
     */
    public void dealEasyExcelXlsFileEntity(Integer start, Integer end, String path, String ext) {
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<JxlTableEntity> jxlTableEntityList = new ArrayList<>();
                try {
                    jxlTableEntityList = easyExcelReadService.readExcel(new BufferedInputStream(new FileInputStream(newpaths)), JxlTableEntity.class);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                map.put("jxlTables", jxlTableEntityList);
                jxlTableInfoMapper.startInsertJxlConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }

    /**
     * @Description: 使用FastExcel处理解析xls文件
     * @param: "[start, end, path, ext]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/25 14:25
     */
    public void dealFastExcelXlsFile(Integer start, Integer end, String path, String ext) {
        long startTime = System.currentTimeMillis();
        try {
            for (int i = start; i < end; i++) {
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                File newpaths = new File(path + 0 + ext);
                List<JxlTable> jxlTables = new ArrayList<>();
                try {
                    jxlTables = resolveFastFile(newpaths);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                map.put("jxlTables", jxlTables);
                jxlTableInfoMapper.startInsertJxlConInfos(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗时间：{}ms", result);
    }

    /**
     * @Description: 处理fastexcel解析文件
     * @param: "[file]"
     * @Return: java.util.List<com.tj.resolvedemo.domain.JxlTable>
     * @Author: supenghui
     * @Date: 2021/3/25 14:52
     */
    private List<JxlTable> resolveFastFile(File file) throws Exception {
        List<JxlTable> jxlTables = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
        String serialNo = sdf.format(new Date());
        edu.npu.fastexcel.Workbook workBook = FastExcel.createReadableWorkbook(file);
        workBook.open();
        edu.npu.fastexcel.Sheet s = workBook.getSheet(0);
        String[] row;
        String cell;
        for (int index = 7; index <= s.getLastRow(); index++) {  //s.getFirstRow()
            row = s.getRow(index);
            if (!StringUtils.isEmpty(row)) {
                JxlTable jxlTable = new JxlTable();
                jxlTable.setSerialNo(serialNo);
                for (int j = s.getFirstColumn(); j < s.getLastColumn(); j++) {
                    cell = s.getCell(index, j);
                    setFieldValueByFieldName(j, cell, jxlTable);
                }
                jxlTables.add(jxlTable);
            }
        }
        workBook.close();
        return jxlTables;
    }

}
