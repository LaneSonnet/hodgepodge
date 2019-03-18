package com.mudfish.thread;

import com.mudfish.exception.MudfishRpcException;
import com.mudfish.factory.RpcResponseFactory;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class RpcFutureResponse {

	private MudfishRpcRequest request;
	private volatile MudfishRpcResponse response;
	private RpcResponseFactory factory;
	private Object lock = new Object();
	private volatile boolean done = false;

	public RpcFutureResponse(MudfishRpcRequest request, RpcResponseFactory factory) {
		this.factory = factory;
		this.request = request;
		factory.addResponse(request.getRequestId(), this);
	}

	public void setResponse(MudfishRpcResponse response) {
		synchronized (lock) {
			done = true;
			this.response = response;
			lock.notifyAll();
		}
	}

	public MudfishRpcResponse get(long timeoutMillis) {
		if (!done) {
			synchronized (lock) {
				try {
					lock.wait(timeoutMillis);
				} catch (InterruptedException e) {
					e.printStackTrace();
					factory.removeResponse(request.getRequestId());
					throw new MudfishRpcException("RequestId=" + response.getRequestId() + " get future response timeout");
				}
			}
		}
		if (!done) {
			factory.removeResponse(request.getRequestId());
			throw new MudfishRpcException("RequestId=" + response.getRequestId() + " get future response timeout");
		}
		return response;
	}
}
