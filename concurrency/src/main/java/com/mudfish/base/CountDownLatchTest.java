package com.mudfish.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	private final CountDownLatch latch = new CountDownLatch(2);

	private ExecutorService executor = Executors.newCachedThreadPool();

	private class Thread1 implements Runnable {

		public void run() {
			System.out.println("thread1 启动");
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread1 执行完毕");
		}

	}

	private class Thread2 implements Runnable {

		public void run() {
			System.out.println("thread2 启动");
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread2 执行完毕");
		}

	}

	private class Thread3 implements Runnable {

		public void run() {
			System.out.println("thread3 启动");
			latch.countDown();
			System.out.println("thread3 执行完毕");
		}

	}

	private class Thread4 implements Runnable {

		public void run() {
			System.out.println("thread4 启动");
			latch.countDown();
			System.out.println("thread4 执行完毕");
		}

	}

	public void runDemo() {
		executor.execute(new Thread1());
		executor.execute(new Thread2());
		executor.execute(new Thread3());
		executor.execute(new Thread4());
	}

	public static void main(String[] args) {

		CountDownLatchTest latchTest = new CountDownLatchTest();
		latchTest.runDemo();


	}

}