package com.mudfish.base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	private final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {

		public void run() {
			System.out.println("栅栏释放");
		}
	});
	private ExecutorService executor = Executors.newCachedThreadPool();

	private class Runner1 implements Runnable {

		public void run() {
			System.out.println("运动员1号准备完毕");
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("运动员1号go go go");
		}

	}

	private class Runner2 implements Runnable {

		public void run() {
			System.out.println("运动员2号准备完毕");
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("运动员2号go go go");
		}

	}

	private class Runner3 implements Runnable {

		public void run() {
			System.out.println("运动员3号准备完毕");
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println("运动员3号go go go");
		}

	}

	public void run() throws InterruptedException {
		CyclicBarrierTest test = new CyclicBarrierTest();
		test.executor.execute(new Runner1());
		test.executor.execute(new Runner2());
		test.executor.execute(new Runner3());
		Thread.sleep(1000);
		test.executor.execute(new Runner1());
		test.executor.execute(new Runner2());
		test.executor.execute(new Runner3());
	}

	public static void main(String[] args) throws InterruptedException {
		CyclicBarrierTest test = new CyclicBarrierTest();
		test.run();
	}


}
