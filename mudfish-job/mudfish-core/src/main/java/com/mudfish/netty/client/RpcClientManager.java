package com.mudfish.netty.client;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.common.constants.RpcServerConstant;
import com.mudfish.common.util.NetUtil;
import com.mudfish.dao.rpc.RpcServerDao;
import com.mudfish.exception.MudfishRpcException;
import com.mudfish.po.rpc.RpcServer;
import com.mudfish.thread.WorkerMoniterThread;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class RpcClientManager {

	private static final Logger logger = LoggerFactory.getLogger(RpcClientManager.class);
	private CopyOnWriteArrayList<NettyClient> connects = new CopyOnWriteArrayList();
	private volatile int clientIndex = 0;
	@Resource
	private RpcServerDao rpcServerDao;

	public RpcClientManager() {
	}

	public void init() {
		List<RpcServer> rpcServers = getAliveRpcServer();
		for (RpcServer rpcServer : rpcServers) {
			NettyClient client = new NettyClient(rpcServer.getIp(), rpcServer.getRpcPort());
			client.setId("" + System.currentTimeMillis() + client.hashCode());
			client.start();
			connects.add(client);
		}
		WorkerMoniterThread workerMoniterThread = new WorkerMoniterThread(connects, rpcServerDao);
		workerMoniterThread.start();
	}

	public void destroy() {
		for (NettyClient connect : connects) {
			connect.close();
		}
	}

	public NettyClient getClient() {
		int nextIndex = 0;
		synchronized (this) {
			if (clientIndex < (connects.size() - 1)) {
				nextIndex = ++clientIndex;
			} else {
				clientIndex = 0;
			}
		}
		NettyClient nettyClient = connects.get(nextIndex);
		return nettyClient;
	}

	public List<RpcServer> getAliveRpcServer() {
		List<RpcServer> rpcServers = rpcServerDao.queryByStatus(RpcServerConstant.STATUS_START);
		Iterator<RpcServer> iterator = rpcServers.iterator();
		while (iterator.hasNext()) {
			RpcServer rpcServer = iterator.next();
			boolean reachable = NetUtil
					.isReachable(rpcServer.getIp(), rpcServer.getRpcPort(), RpcServerConstant.PORT_REACHABLE_TIME_OUT);
			if (!reachable) {
				iterator.remove();
			}
		}
		if (rpcServers == null || rpcServers.isEmpty()) {
			throw new MudfishRpcException("There has not available remote rpc server");
		}
		return rpcServers;
	}

}
