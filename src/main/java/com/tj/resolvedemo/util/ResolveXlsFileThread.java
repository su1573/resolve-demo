package com.tj.resolvedemo.util;

import com.tj.resolvedemo.config.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * @program: opension
 * @Date: 2020/10/14 16:07
 * @Author: Mr.SU
 * @Description: 线程调用处理逻辑
 */
public class ResolveXlsFileThread implements Runnable {
    protected Logger logger = LoggerFactory.getLogger(ResolveXlsFileThread.class);
    private String path;
    private String ext;
    private Integer start;
    private Integer end;


    public ResolveXlsFileThread() {
    }

    public ResolveXlsFileThread(Integer start, Integer end, String path, String ext) {
        this.path = path;
        this.ext = ext;
        this.start = start;
        this.end = end;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public void run() {
        try {
            ApplicationContext ac = BeanFactory.getApplicationContext();
            ResolveMDBFileThreadUtil resolveMDBFileThreadUtil = (ResolveMDBFileThreadUtil) ac.getBean("resolveMDBFileThreadUtil");
//            logger.info("---------- 线程名称：" + Thread.currentThread().getName() + " 开始工作 ----------");
            resolveMDBFileThreadUtil.dealJxlXlsFile(start, end, path, ext);
//            logger.info("---------- 线程名称：" + Thread.currentThread().getName() + " 结束工作 ----------");
        } catch (Exception e) {
            logger.info("---------- 线程调用出错，线程名称：" + Thread.currentThread().getName() + " ----------");
            e.printStackTrace();
        }
    }
}
