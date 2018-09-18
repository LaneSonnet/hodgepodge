package com.mudfish.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private int ticket = 0;
	private final ReentrantLock lock = new ReentrantLock();
	final Condition condition = lock.newCondition();

	public int getTicket() {
		lock.lock();
		try {
			int num = ticket--;
			return num;
		} finally {
			lock.unlock();
		}
	}

	public void product() {
		lock.lock();
		try {
			ticket++;
			System.out.println("生产" + ticket);
			condition.signal();
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public int consume() {
		lock.lock();
		try {
			condition.await();
			int num = ticket;
			System.out.println("消费" + num);
			condition.signal();
			return num;
		}catch (InterruptedException e) {
			return -1;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		final ReentrantLockTest test = new ReentrantLockTest();
		/*final CountDownLatch latch = new CountDownLatch(1);

		for (int i=0; i<10; i++) {
			new Thread(new Runnable() {

				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "准备开抢");
						latch.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
					while (true) {
						int no = test.getTicket();
						System.out.println(Thread.currentThread().getName() + ":获取到一张票：" + no);
						if (no <= 0 ){
							break;
						}
					}
				}
			}).start();
		}
		
		Thread.sleep(3000);
		System.out.println("ok, let`s go!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		latch.countDown();*/

		new Thread(new Runnable() {

			public void run() {
				while (true) {
					test.consume();
				}
			}
		}).start();
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					test.product();
				}
			}
		}).start();

	}
}
