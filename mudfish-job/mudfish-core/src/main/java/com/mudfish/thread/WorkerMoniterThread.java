package com.mudfish.thread;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.common.constants.RpcServerConstant;
import com.mudfish.common.util.NetUtil;
import com.mudfish.dao.rpc.RpcServerDao;
import com.mudfish.exception.MudfishRpcException;
import com.mudfish.netty.client.NettyClient;
import com.mudfish.po.rpc.RpcServer;

/**
 * Created by Mudfish on 2019/3/2 0002.
 */
public class WorkerMoniterThread implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(WorkerMoniterThread.class);
	private volatile CopyOnWriteArrayList<NettyClient> nettyClients;
	private volatile RpcServerDao rpcServerDao;

	public WorkerMoniterThread(CopyOnWriteArrayList<NettyClient> nettyClients, RpcServerDao rpcServerDao) {
		this.nettyClients = nettyClients;
		this.rpcServerDao = rpcServerDao;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				clearDeadClient(nettyClients);
				addNewClient();
			} catch (Exception e) {
				logger.error("Exception occured in client monitor thread：" + e);
			}
			try {
				TimeUnit.SECONDS.sleep(RpcServerConstant.CLIENT_MONITER_INTERVAL_TIME);
			} catch (InterruptedException e) {
				logger.error("Client monitor thread is interrupted!：" + e);
				throw new MudfishRpcException("Client monitor thread is interrupted!");
			}
		}
	}

	/**
	 * 检查数据新增的worker
	 */
	private void addNewClient() {
		List<RpcServer> rpcServers = rpcServerDao.queryByStatus(RpcServerConstant.STATUS_START);
		Iterator<RpcServer> iterator = rpcServers.iterator();
		while (iterator.hasNext()) {
			RpcServer server = iterator.next();
			if (hasConnect(server)) {
				continue;
			}
			boolean reachable = NetUtil.isReachable(server.getIp(), server.getRpcPort(), RpcServerConstant
					.PORT_REACHABLE_TIME_OUT);
			if (reachable) {
				newAndAddClient(server);
			}
		}
	}

	private void newAndAddClient(RpcServer server) {
		NettyClient client = new NettyClient(server.getIp(), server.getRpcPort());
		client.setId("" + System.currentTimeMillis() + client.hashCode());
		client.start();
		nettyClients.add(client);
	}

	private boolean hasConnect(RpcServer server) {
		boolean hasConnect = false;
		for (NettyClient client : nettyClients) {
			if (client.getHost().equals(server.getIp()) && client.getPort() == server.getRpcPort()) {
				hasConnect = true;
				break;
			}
		}
		return hasConnect;
	}

	/**
	 * 三次重试之后宣告死亡清除
	 */
	private void clearDeadClient(CopyOnWriteArrayList<NettyClient> nettyClients) {
		Iterator<NettyClient> it = nettyClients.iterator();
		while (it.hasNext()) {
			NettyClient client = it.next();
			if (!client.getThread().isAlive()) {
				nettyClients.remove(client);
			}
		}
	}

}