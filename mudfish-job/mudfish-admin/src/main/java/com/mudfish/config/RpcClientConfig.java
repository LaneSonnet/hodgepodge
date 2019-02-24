package com.mudfish.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mudfish.netty.client.RpcClientManager;

/**
 * Created by Mudfish on 2019/2/20 0020.
 */
@Configuration
@ConfigurationProperties(prefix = "mudfish-admin")
public class RpcClientConfig {

	private List<String> serverParams = new ArrayList<>();

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public RpcClientManager rpcClientManager() {
		RpcClientManager rpcClientManager = new RpcClientManager(serverParams);
		return rpcClientManager;
	}

}
