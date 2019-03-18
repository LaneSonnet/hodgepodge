package com.mudfish.netty.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.mudfish.dao.rpc.RpcServerDao;
import com.mudfish.factory.RpcInvokeFactory;

/**
 * Created by Mudfish on 2019/2/20 0020.
 */
public class RpcServerManager implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(RpcInvokeFactory.class);
	private int rpcPort;
	private NettyServer server;
	private ApplicationContext applicationContext;
	@Resource
	private RpcServerDao rpcServerDao;

	public void start() throws Exception {
		RpcInvokeFactory invokeFactory = initRpcInvokeFactory();
		startServer(invokeFactory);
	}

	private RpcInvokeFactory initRpcInvokeFactory() {
		return new RpcInvokeFactory(applicationContext);
	}

	private void startServer(RpcInvokeFactory invokeFactory) throws UnknownHostException {
		rpcServerDao.delete(InetAddress.getLocalHost().getHostAddress(), rpcPort);
		server = new NettyServer(invokeFactory);
		server.setRpcServerDao(rpcServerDao);
		server.start(rpcPort);
	}

	public void destroy() {
		server.stop();
		logger.info("mudifsh server stop success!");
	}

	public void setRpcPort(int rpcPort) {
		this.rpcPort = rpcPort;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
