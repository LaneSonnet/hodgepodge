package com.mudfish.test.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.mudfish.test.netty.pool.NioSelectorRunnablePool;
/**
 * 启动函数
 *
 */
public class Start {

	public static void main(String[] args) {

		
		//初始化线程
		NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		
		//获取服务类
		ServerBootstrap bootstrap = new ServerBootstrap(nioSelectorRunnablePool);
		
		//绑定端口
		bootstrap.bind(new InetSocketAddress(10101));
		
		System.out.println("start");
	}

}
