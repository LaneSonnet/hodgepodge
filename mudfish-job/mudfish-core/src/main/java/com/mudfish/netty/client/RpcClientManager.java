package com.mudfish.netty.client;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class RpcClientManager {

	private CopyOnWriteArrayList<NettyClient> connects = new CopyOnWriteArrayList();
	private volatile int clientIndex = 0;
	private List<String> serverParams;

	public RpcClientManager(List<String> serverParams) {
		this.serverParams = serverParams;
	}

	public CopyOnWriteArrayList<NettyClient> init() {
		for (String serverParam : serverParams) {
			String[] params = serverParam.split(":");
			NettyClient client = new NettyClient(params[0], Integer.parseInt(params[1]));
			client.setId("" + System.currentTimeMillis() + client.hashCode());
			client.start();
			connects.add(client);
		}
		return connects;
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
}
