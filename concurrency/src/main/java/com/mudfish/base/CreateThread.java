package com.mudfish.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Mudfish on 2019/3/30 0030.
 */
public class CreateThread {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<Integer> future = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return 2;
			}
		});
		Thread thread = new Thread(future);
		thread.start();
		System.out.println(future.get());
	}


}
