package com.mudfish.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mudfish.netty.server.RpcServerManager;

/**
 * Created by Mudfish on 2019/2/20 0020.
 */
@Configuration
public class RpcConfig {

	@Value("${mudfishWorker.rpc.port}")
	private int rpcPort;

	@Bean(initMethod = "start", destroyMethod = "destroy")
	public RpcServerManager xxlJobExecutor() {
		RpcServerManager manager = new RpcServerManager();
		manager.setRpcPort(rpcPort);
		return manager;
	}
}
