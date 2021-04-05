package com.tj.resolvedemo.controller;

import com.tj.resolvedemo.domain.Gzb;
import com.tj.resolvedemo.domain.GzbTable;
import com.tj.resolvedemo.mapper.GzbInfoMapper;
import com.tj.resolvedemo.test.ResolveFile;
import com.tj.resolvedemo.util.ExecShortMessageThreadUtil;
import com.tj.resolvedemo.util.ResolveMDBFileThread;
import com.tj.resolvedemo.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 13:56
 * @Author: Mr.SU
 * @Description: 解析MDB文件
 */
@RestController
public class MdbGzbReadController {

    private static Logger logger = LoggerFactory.getLogger(MdbGzbReadController.class);

    @Value("${targetMDB}")
    private String target;


//    @Autowired
//    private GzbRepository gzbRepository;

    @Autowired
    private GzbInfoMapper gzbInfoMapper;


    /**
     * @Description:测试解析一个mdb文件
     * @param: "[]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/18 14:48
     */
    @GetMapping("testMDB")
    public ResultBean testResolveMDBFile() {
        ResolveFile rmf = new ResolveFile();
//        String target = "D:\\00-2021-mdb\\test1000\\";
        String fileName = "0315-file";
        String ext = ".mdb";
        long startTime = System.currentTimeMillis();
        try {
            File newpaths = new File(target + fileName + 0 + ext);
            List<Gzb> gzbList = rmf.readFileSingalTableACCESS(newpaths); //读取单表
            List<GzbTable> gzbTables = new ArrayList<>();
            for (Gzb gzb : gzbList) {
                GzbTable gzbTable = new GzbTable();
                BeanUtils.copyProperties(gzb, gzbTable);
                gzbTables.add(gzbTable);
            }
//            gzbRepository.saveAll(gzbTables); mysql使用时插入
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info("消耗时间：{}ms", result);

        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }

    /**
     * @Description: 测试解析多个mdb文件
     * @param: "[]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/18 15:05
     */
    @GetMapping("testMulMDB")
    public ResultBean testResolveMulMDBFile(Integer num, String fileName) {
        ResolveFile rmf = new ResolveFile();
//        target = "D:\\00-2021-mdb\\test1000\\";

        String ext = ".mdb";
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < num; i++) {
                File newpaths = new File(target + fileName + 0 + ext);
                List<Gzb> gzbList = rmf.readFileSingalTableACCESS(newpaths); //读取单表
//                gzbRepository.saveAll(gzbList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info("消耗时间：{}ms", result);

        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }

    /**
     * @Description: 测试sql语句添加数据
     * @param: "[]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/21 22:30
     */
    @GetMapping("testInsert")
    public ResultBean testInsertGzb() {
        long startTime = System.currentTimeMillis();

        HashMap<String, Object> map = new HashMap<>();
        List<Gzb> gzbList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Gzb gzb = new Gzb();
            gzb.setFBJSJ(Double.parseDouble("8888" + i));
            gzb.setFBJSJ_GZZZ(Double.parseDouble("9999" + i));
            gzbList.add(gzb);
        }
        map.put("gzbInfos", gzbList);
        gzbInfoMapper.insertGzbInfos(map);
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info("消耗时间：{}ms", result);
        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }

    /**
     * @Description: 单线程解析文件
     * @param: "[]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/21 22:53
     */
    @GetMapping("startInsertGzb")
    public ResultBean startInsertGzb(Integer num, String fileName) {
        ResolveFile rmf = new ResolveFile();
//        String target = "D:\\00-2021-mdb\\test1000\\";
//        target = "//usr//local//testmdb//";

        String ext = ".mdb";
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < num; i++) {
                HashMap<String, Object> map = new HashMap<>();
                File newpaths = new File(target + fileName + 0 + ext);
                List<Gzb> gzbList = rmf.readFileSingalTableACCESS(newpaths); //读取单表
                map.put("gzbInfos", gzbList);
                gzbInfoMapper.startInsertGzbInfos(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info("消耗时间：{}ms", result);
        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }

    /**
     * @Description: 根据文件名和数量复制文件相应的数量
     * @param: "[fileName, num]"
     * @Return: void
     * @Author: supenghui
     * @Date: 2021/3/29 9:39
     */
    @GetMapping("copyFile")
    public void copyFile(String fileName, Integer num) {
        String source = target + fileName + ".mdb";
//        String target = "/usr/local/testmdb/";
//        String target = "C:\\01-testmdb\\test1000\\";
//        String fileName = "0315-file";
        String ext = ".mdb";

        //直接复制
        File file = new File(source); //共享存储文件


        for (int i = 0; i < num; i++) {
            File newpaths = new File(target + fileName + i + ext);
            //复制文件到指定目录
            try {
                if (!newpaths.exists()) {
                    Files.copy(file.toPath(), newpaths.toPath());
                } else {
                    newpaths.delete();
                    Files.copy(file.toPath(), newpaths.toPath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: 多线程异步，不等待执行完毕直接返回结果
     * @param: "[num, start, fileName]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/22 19:14
     */
    @GetMapping("startInsertGzbThread")
    public ResultBean startInsertGzbThread(Integer num, String fileName, Integer threadNum) {
//        String target = "D:\\00-2021-mdb\\test1000\\"; //本地路径
//        target = "//usr//local//testmdb//";  //服务器路径

        String ext = ".mdb";
        String path = target + fileName;
        int start = num / threadNum;
        int mod = num % threadNum;
        int length = threadNum;
        logger.info("start:{},mod:{},length:{}", start, mod, length);

        //有余数，循环次数加1

        //0 -- 2*1 2*1 -- 2*2   2*2 -- 2*3  2*3 -- 2*4  2*4 -- 2*5
        //0 -- 3*1 3*1 -- 3*2   3*2 -- 3*3  3*3 -- 3*4
        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < length; i++) {
                new ExecShortMessageThreadUtil(start * i, start * (i + 1), path, ext);
            }
            if (mod != 0 && length != 1) {
                new ExecShortMessageThreadUtil(start * threadNum, num, path, ext);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }

    /**
     * @Description: 多线程异步，等待多执行完成后反馈消耗总时间
     * @param: "[num, fileName, threadNum]"
     * @Return: com.tj.resolvedemo.util.ResultBean
     * @Author: supenghui
     * @Date: 2021/3/23 11:25
     */
    @GetMapping("startInsertGzbThreadAll")
    public ResultBean startInsertGzbThreadAll(Integer num, String fileName, Integer threadNum) {
//        String target = "D:\\00-2021-mdb\\test1000\\"; //本地路径
//        target = "//usr//local//testmdb//";  //服务器路径

        String ext = ".mdb";
        String path = target + fileName;
        int start = num / threadNum;
        int mod = num % threadNum;
        int length = threadNum;
        logger.info("start:{},mod:{},length:{}", start, mod, length);

        //有余数，循环次数加1

        //0 -- 2*1 2*1 -- 2*2   2*2 -- 2*3  2*3 -- 2*4  2*4 -- 2*5
        //0 -- 3*1 3*1 -- 3*2   3*2 -- 3*3  3*3 -- 3*4
        long startTime = System.currentTimeMillis();
        try {
//            ThreadPoolExecutor executorService = InitThreadPoolServlet.getExecutor();
            ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
            for (int i = 0; i < length; i++) {
                executorService.execute(new ResolveMDBFileThread(start * i, start * (i + 1), path, ext));
            }
            if (mod != 0 && length != 1) {
                executorService.execute(new ResolveMDBFileThread(start * threadNum, num, path, ext));
            }
            /*while (true) {
                if (executorService.getPoolSize() == executorService.getCompletedTaskCount()) {
                    //线程池中线程数目 == 已执行完的任务数目
                    break;
                }
            }*/

            // 所有任务执行完成且等待队列中也无任务关闭线程池
            executorService.shutdown();
            // 阻塞主线程, 直至线程池关闭
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        logger.info(Thread.currentThread().getName() + "消耗总时间：{}ms", result);
        return ResultBean.success("解析成功，消耗时间：" + result + "ms");
    }


}
