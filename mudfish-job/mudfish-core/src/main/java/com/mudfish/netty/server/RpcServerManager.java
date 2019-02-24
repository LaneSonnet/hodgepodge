package com.mudfish.netty.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mudfish.factory.RpcInvokeFactory;

/**
 * Created by Mudfish on 2019/2/20 0020.
 */
public class RpcServerManager implements ApplicationContextAware {

	private int rpcPort;
	private NettyServer server;
	private ApplicationContext applicationContext;

	public void start() throws Exception {
		RpcInvokeFactory invokeFactory = initRpcInvokeFactory();
		startServer(invokeFactory);
	}

	private RpcInvokeFactory initRpcInvokeFactory() {
		return new RpcInvokeFactory(applicationContext);
	}

	private void startServer(RpcInvokeFactory invokeFactory) {
		server = new NettyServer(invokeFactory);
		server.start(rpcPort);
	}

	public void destroy() {
		server.stop();
	}

	public void setRpcPort(int rpcPort) {
		this.rpcPort = rpcPort;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
