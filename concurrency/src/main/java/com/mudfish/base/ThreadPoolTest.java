package com.mudfish.base;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) throws InterruptedException {

//		 ExecutorService threadPool = Executors.newFixedThreadPool(2);
//		 ExecutorService threadPool = Executors.newCachedThreadPool();
//		 ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);
//		 ExecutorService threadPool = Executors.newSingleThreadExecutor();
		 
/*		 MyTask task1 = new MyTask("1");
		 MyTask task2 = new MyTask("2");
		 MyTask task3 = new MyTask("3");
		 MyTask task4 = new MyTask("4");
		 MyTask task5 = new MyTask("5");
		 MyTask task6 = new MyTask("6");
		 MyTask task7 = new MyTask("7");
 
		 threadPool.execute(task1);
		 threadPool.execute(task2);
		 threadPool.execute(task3);
		 threadPool.execute(task4);
		 threadPool.execute(task5);
		 threadPool.execute(task6);
		 threadPool.execute(task7);*/

//		 threadPool.scheduleAtFixedRate(task1, 1, 3, TimeUnit.SECONDS);
//		 threadPool.scheduleWithFixedDelay(task1, 1, 1, TimeUnit.SECONDS);
		  
		 /*
		  * 有界队列：当任务数大于corePoolSize时进入队列，当队列满时在不大于maxPoolSize时优先创建线程，否则拒绝
		  */
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
		 /*
		  * 无界队列：maxPoolSize无效
		  */
//		 ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

//			ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
//					new ArrayBlockingQueue<Runnable>(2));
//			MyTask task1 = new MyTask("1");
//			MyTask task2 = new MyTask("2");
//			MyTask task3 = new MyTask("3");
//			MyTask task4 = new MyTask("4");
//			MyTask task5 = new MyTask("5");
//			MyTask task6 = new MyTask("6");
//		    MyTask task7 = new MyTask("7");

//			threadPool.execute(task1);
//			threadPool.execute(task2);
//			threadPool.execute(task3);
//			threadPool.execute(task4);
//			threadPool.execute(task5);
//			threadPool.execute(task6);
//			Thread.sleep(4000);
//			threadPool.execute(task7);

		ArrayList<MyTask> list = new ArrayList<MyTask>();
		list.add(new MyTask("1"));
		list.add(new MyTask("2"));
		list.add(new MyTask("3"));
		list.add(new MyTask("4"));
		list.add(new MyTask("5"));
		list.add(new MyTask("6"));
		list.add(new MyTask("7"));

		for (MyTask myTask : list) {
			try {
//					if ("7".equals(myTask.getTaskId())) {
//						Thread.sleep(3000);
//					}
				threadPool.execute(myTask);
			} catch (Exception e) {
				System.out.println("oh my god");
			}
		}















	}
}
