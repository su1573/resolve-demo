package com.tj.resolvedemo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @program:
 * @Date: 2020/10/22 15:00
 * @Author: Mr.SU
 * @Description: 初始化线程池
 */
@WebServlet("/InitThreadPoolServlet")
public class InitThreadPoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
	private static ThreadPoolExecutor  executor  = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

	protected Logger logger = LoggerFactory.getLogger(InitThreadPoolServlet.class);

	public InitThreadPoolServlet() {
	}

	public static ThreadPoolExecutor getExecutor() {
		return executor;
	}

	public static void setExecutor(ThreadPoolExecutor executor) {
		InitThreadPoolServlet.executor = executor;
	}

	public static ExecutorService getExecutorService() {
		return executorService;
	}

	public static void setExecutorService(ExecutorService executorService) {
		InitThreadPoolServlet.executorService = executorService;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
