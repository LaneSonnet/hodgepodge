package com.mudfish.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {


	public static void main(String[] args) throws InterruptedException {

		FutureTask<List<Customer>> future = new FutureTask<List<Customer>>(new Callable<List<Customer>>() {

			public List<Customer> call() throws Exception {
				System.out.println(Thread.currentThread().getName() + ":::11开始加载");
				Thread.sleep(3000);
				List<Customer> list = new ArrayList<Customer>();
				list.add(new Customer(1, "wangwu"));
				list.add(new Customer(2, "lisi"));
				list.add(new Customer(3, "zhangsan"));
				list.add(new Customer(4, "zhaoliu"));
				list.add(new Customer(5, "jiangqi"));
				list.add(new Customer(6, "liuer"));
				System.out.println(Thread.currentThread().getName() + ":::11加载结束");
				if (true) {
					throw new Exception("异步加载异常");
				}
				return list;
			}
		});

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(future);

		System.out.println(Thread.currentThread().getName() + ":::22开始加载");
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getName() + ":::22加载结束");

		List<Customer> list = null;
		try {
			list = future.get();
		} catch (ExecutionException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(list);


	}
}
