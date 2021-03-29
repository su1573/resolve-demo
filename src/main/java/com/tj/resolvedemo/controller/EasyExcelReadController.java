package com.tj.resolvedemo.controller;

import com.tj.resolvedemo.util.ResolveXlsEasyEntityFileThread;
import com.tj.resolvedemo.util.ResolveXlsEasyFileThread;
import com.tj.resolvedemo.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: resolve-demo
 * @Date: 2021/3/25 10:28
 * @Author: Mr.SU
 * @Description: EasyExcel解析XLS文件
 */
@RestController
@RequestMapping("/easy")
public class EasyExcelReadController {

    private static Logger logger = LoggerFactory.getLogger(EasyExcelReadController.class);

    @Value("${target}")
    private String target;

    /**
    * @Description: 多线程调用，解析excel，不使用实体类映射
    * @param: "[num, fileName, threadNum]"
    * @Return: com.tj.resolvedemo.util.ResultBean
    * @Author: supenghui
    * @Date: 2021/3/25 10:30
     * 调用：
    */
    @GetMapping("/startInsertThreadAll")
    public ResultBean startInsertEasyExcelThreadAll(Integer num, String fileName, Integer threadNum) {

        String ext = ".xls";
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
            ThreadPoolExecutor executorService  = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
            for (int i = 0; i < length; i++) {
                executorService.execute(new ResolveXlsEasyFileThread(start*i, start*(i+1), path, ext));
            }
            if (mod != 0 && length != 1) {
                executorService.execute(new ResolveXlsEasyFileThread(start * threadNum, num, path, ext));
            }
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


    /**
    * @Description: 多线程调用，解析excel，使用实体类映射
    * @param: "[num, fileName, threadNum]"
    * @Return: com.tj.resolvedemo.util.ResultBean
    * @Author: supenghui
    * @Date: 2021/3/25 11:24
    */
    @GetMapping("/startInsertThreadAll_Entity")
    public ResultBean startInsertEasyExcelEntityThreadAll(Integer num, String fileName, Integer threadNum) {

        String ext = ".xls";
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
            ThreadPoolExecutor executorService  = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
            for (int i = 0; i < length; i++) {
                executorService.execute(new ResolveXlsEasyEntityFileThread(start*i, start*(i+1), path, ext));
            }
            if (mod != 0 && length != 1) {
                executorService.execute(new ResolveXlsEasyEntityFileThread(start * threadNum, num, path, ext));
            }
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
