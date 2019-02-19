package com.mudfish.rpc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mudfish.netty.client.NettyClient;

/**
 * Created by Mudfish on 2019/2/19 0019.
 */
@Component
public class RpcClientRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		NettyClient nettyClient = new NettyClient("127.0.0.1", 8090);
		nettyClient.start();
	}
}
