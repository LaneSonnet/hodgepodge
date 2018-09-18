package com.mudfish.base;

public class VolatileTest2 {
	private volatile int a = 1;
	
	
	public int getA() {
		return a;
	}


	public void setA(int a) {
		this.a = a;
	}


	public static void main(String[] args) {
		final VolatileTest2 test2 = new VolatileTest2();
		new Thread(new Runnable() {
			public void run() {
				test2.setA(2);
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println(test2.getA());
			}
		}).start();
		
	}
}
