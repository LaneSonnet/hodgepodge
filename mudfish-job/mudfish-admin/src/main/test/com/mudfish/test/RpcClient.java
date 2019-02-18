package com.mudfish.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.mudfish.constants.MessageType;
import com.mudfish.netty.client.NettyClient;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class RpcClient {

	public static void main(String[] args) throws Exception {
		NettyClient nettyClient = new NettyClient("127.0.0.1", 8009);
		nettyClient.start();
		MudfishRpcRequest request = new MudfishRpcRequest();
		request.setRequestId("12345678");
		MudfishMessage message = new MudfishMessage(MessageType.NORMAL, request);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = reader.readLine();
			request.setRequestId(str);
			nettyClient.send(message);
		}
	}
}
