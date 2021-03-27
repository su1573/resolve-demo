package com.tj.resolvedemo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 10:45
 * @Author: Mr.SU
 * @Description:
 */
public class ThreadResolveFile {
    private static Logger logger = LoggerFactory.getLogger(ResolveFile.class);
    private String target = "D:\\00-2021-mdb\\test1000\\";
    private String fileName = "0315-file";
    private String ext = ".mdb";

    public static void main(String[] args) {
        ThreadResolveFile threadResolveFile = new ThreadResolveFile();
        threadResolveFile.testOneThread();
//        threadResolveFile.testTwoThread();
//        threadResolveFile.testFourThread();
        ExecutorService executorService = Executors.newWorkStealingPool();
    }
    public void testFourThread() {
        Thread oneT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 2; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间1：{}s", (endTime - startTime) / 1000);
        });
        Thread twoT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 25; i < 27; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间2：{}s", (endTime - startTime) / 1000);
        });
        Thread threeT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 50; i < 52; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间3：{}s", (endTime - startTime) / 1000);
        });
        Thread fourT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 75; i < 77; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间4：{}s", (endTime - startTime) / 1000);
        });
        oneT.start();
        twoT.start();
        threeT.start();
        fourT.start();
        try {
            TimeUnit.SECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
    * @Description: 两个线程  每个线程运行
    * @param: "[]"
    * @Return: void
    * @Author: supenghui
    * @Date: 2021/3/18 11:27
    */
    public void testOneThread() {
        Thread oneT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 5; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间1：{}s", (endTime - startTime) / 1000);
        });
        oneT.start();
        /*try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    public void testTwoThread() {
        Thread oneT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 5; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间1：{}s", (endTime - startTime) / 1000);
        });

        Thread twoT = new Thread(()->{
            ResolveFile rmf = new ResolveFile();
            long startTime = System.currentTimeMillis();
            for (int i = 10; i < 15; i++) {
                File newpaths = new File(target + fileName + i + ext);
                rmf.readFileSingalTableACCESS(newpaths);
            }
            long endTime = System.currentTimeMillis();
            logger.info("消耗时间1：{}s", (endTime - startTime) / 1000);
        });
        oneT.start();
        twoT.start();
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
