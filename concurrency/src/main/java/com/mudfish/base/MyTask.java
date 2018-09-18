package com.mudfish.base;

public class MyTask implements Runnable{

	private final String taskId;

	public MyTask(String taskId){
		this.taskId = taskId;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + "+++++任务" + taskId + "执行");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("任务" + taskId + "执行结束");
	}

	public String getTaskId() {
		return taskId;
	}




}
