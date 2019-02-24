package com.mudfish.factory;

import java.util.concurrent.ConcurrentHashMap;

import com.mudfish.struct.MudfishRpcResponse;
import com.mudfish.thread.RpcFutureResponse;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class RpcResponseFactory {

	private static ConcurrentHashMap<String, RpcFutureResponse> responsePool = new ConcurrentHashMap<String,
			RpcFutureResponse>();
	private static volatile RpcResponseFactory instance = new RpcResponseFactory();

	private RpcResponseFactory() {
	}

	public static RpcResponseFactory getInstance() {
		return instance;
	}

	public void addResponse(String requestId, RpcFutureResponse response) {
		responsePool.put(requestId, response);
	}

	public void removeResponse(String requestId) {
		responsePool.remove(requestId);
	}

	public void notifyFutureResponse(String requestId, MudfishRpcResponse response) {
		RpcFutureResponse rpcFutureResponse = responsePool.get(requestId);
		if (rpcFutureResponse == null) {
			return;
		}
		rpcFutureResponse.setResponse(response);
		responsePool.remove(requestId);
	}
}
