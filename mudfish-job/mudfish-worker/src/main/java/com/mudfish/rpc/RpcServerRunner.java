package com.mudfish.rpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mudfish.netty.server.NettyServer;

/**
 * Created by Mudfish on 2019/2/19 0019.
 */
@Component
public class RpcServerRunner implements ApplicationRunner {

	@Value("${mudfishWorker.rpc.port}")
	private int rpcPort;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		NettyServer server = new NettyServer();
		server.start(rpcPort);
	}
}
