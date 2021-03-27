package com.tj.resolvedemo.test;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.tj.resolvedemo.domain.Gzb;
import org.apache.commons.lang.time.DateUtils;
import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: resolve-demo
 * @Date: 2021/3/17 19:32
 * @Author: Mr.SU
 * @Description:
 */
public class ResolveFile {

    public static Logger logger = LoggerFactory.getLogger(ResolveFile.class);

    /**
    * @Description: 解析单表数据 Gzb
    * @param: "[mdbFile]"
    * @Return: java.util.List<com.tj.resolvedemo.domain.Gzb>
    * @Author: supenghui
    * @Date: 2021/3/18 14:52
    */
    public List<Gzb> readFileSingalTableACCESS(File mdbFile) {
        List<Gzb> listG = new ArrayList<>();
        try {
            if (mdbFile.exists()) {
                Database dbin = Database.open(mdbFile);//读文件
                //单表
                Table table = dbin.getTable("Gzb");//获取对应表结构
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssssss");
                String serialNo = sdf.format(new Date());
                Object o = new Object();

//                synchronized (o) {
                    for (Map<String, Object> map : table) {//遍历表数据
                        Set key = map.keySet();
                        setGZBEntity(serialNo, listG, key, map);
                    }
//                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listG;
    }

    /**
    * @Description: 读取多表数据
    * @param: "[mdbFile]"
    * @Return: java.util.List<java.util.Map>
    * @Author: supenghui
    * @Date: 2021/3/18 14:49
    */
    public List<Map> readFileTablesACCESS(File mdbFile) {
        List<Map> maplist = new ArrayList();
//        File mdbFile = new File(filePath);
        try {
            if (mdbFile.exists()) {
                Database dbin = null;
                dbin = Database.open(mdbFile);//读文件
                Set<String> tables = dbin.getTableNames();
//                logger.info("所有表名：{}", tables);

                //遍历所有表名
                for (String t : tables) {
                    ConcurrentHashMap mapTable = new ConcurrentHashMap();
                    mapTable.put("tableName", t);
                    Table table = dbin.getTable(t);//获取对应表结构
                    List<Map> list = new ArrayList();
                    for (Map<String, Object> map : table) {//遍历表数据
                        list.add(map);
                    }
//                    logger.info("数据：{}：{}", t, list);
                    mapTable.put("data", list);
                    maplist.add(mapTable);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maplist;
    }

    private void setGZBEntity(String serialNo, List<Gzb> listG, Set key, Map<String, Object> map) {
        Gzb gzb = new Gzb();
        gzb.setSerialNo(serialNo);
        Iterator iterator = key.iterator();
        while (iterator.hasNext()) {
            String column = (String) iterator.next();
            Object value = map.get(column);
            setFieldValueByFieldName(column, value, gzb);
        }
//        logger.info(gzb.toString());
        listG.add(gzb);
    }

    private void setFieldValueByFieldName(String fieldName, Object value, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); //开通set权限
            field.set(object, value);
        } catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 测试
     *
     * @param args
     */

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        ResolveFile rmf = new ResolveFile();
        String target = "D:\\00-2021-mdb\\test1000\\";
        String fileName = "0315-file";
        String ext = ".mdb";
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            File newpaths = new File(target + fileName + i + ext);
            rmf.readFileSingalTableACCESS(newpaths);
        }
        long endTime = System.currentTimeMillis();
        logger.info("消耗时间：{}s", (endTime - startTime) / 1000);
        //单表解析 1021条数据   不存库
        //100   11s
        //200   13s
        //500   47s
        //1000  58s

        //全表解析  不存库
        //100   253s


//        File mdbFile = new File("D:\\00-2021-mdb\\上海嘉实0315_民生.mdb");
//        rmf.readFileACCESS(mdbFile);
//        rmf.copyFile();


    }



}
