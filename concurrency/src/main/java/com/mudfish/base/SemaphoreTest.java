package com.mudfish.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	private final Semaphore semaphore = new Semaphore(3);

	/**
	 * 和实际安检稍有不同：此方法保持进行安检的始终是三个人
	 */
	public void getInSubway() {
		try {
			System.out.println(Thread.currentThread().getName() + "到达门口");
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + "获得许可，进行安检");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "安检结束");
			semaphore.release();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final SemaphoreTest test = new SemaphoreTest();

		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					test.getInSubway();
				}
			});
//			Thread.sleep(100);
		}
	}

}
