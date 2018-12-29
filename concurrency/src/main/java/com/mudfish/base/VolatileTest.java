package com.mudfish.base;

public class VolatileTest extends Thread {

	private volatile boolean flag = true;

	@Override
	public void run() {
		int i = 0;
		while (flag) {
			System.out.println(i);
			i++;
		}
		System.out.println("thread stopppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
	}

	public void stopMe() {
		flag = false;
		System.out.println("stop me mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

	}

	public static void main(String[] args) throws InterruptedException {
		VolatileTest test = new VolatileTest();
		test.start();
		Thread.sleep(1000);
		test.stopMe();
		Thread.sleep(3000);
		System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
	}

}
