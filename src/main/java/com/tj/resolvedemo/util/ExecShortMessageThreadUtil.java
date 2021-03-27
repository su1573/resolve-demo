package com.tj.resolvedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @program: opension
 * @Date: 2020/10/22 16:30
 * @Author: Mr.SU
 * @Description: 使用线程池调用线程总入口
 */
@Component
public class ExecShortMessageThreadUtil {
    protected Logger logger = LoggerFactory.getLogger(ExecShortMessageThreadUtil.class);
    private String path;
    private String ext;
    private Integer start;
    private Integer end;

    private ExecutorService executorService = InitThreadPoolServlet.getExecutorService();

    public ExecShortMessageThreadUtil() {
    }

    public ExecShortMessageThreadUtil(Integer start, Integer end, String path, String ext) {
        this.path = path;
        this.ext = ext;
        this.start = start;
        this.end = end;
        executorService.submit(new ResolveMDBFileThread(start, end, path, ext));
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
}
