package com.mudfish.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.mudfish.common.constants.RpcServerConstant;
import com.mudfish.constants.MessageType;
import com.mudfish.exception.MudfishRpcException;
import com.mudfish.netty.client.NettyClient;
import com.mudfish.netty.client.RpcClientManager;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;
import com.mudfish.thread.RpcFutureResponse;

/**
 * Created by Mudfish on 2019/2/23 0023.
 */
public class RpcInvokeHandler implements InvocationHandler {

	private static final Logger logger = LoggerFactory.getLogger(RpcInvokeHandler.class);

	private RpcClientManager clientManager;

	public RpcInvokeHandler(RpcClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		logger.info("方法名【{}】，参数【{}】", method.getName(), args[0].toString());
		MudfishRpcRequest request = new MudfishRpcRequest();
		long currentTime = System.currentTimeMillis();
		request.setRequestId(currentTime + UUID.randomUUID().toString());
		request.setCreateMillisTime(currentTime);
		request.setClassName(method.getDeclaringClass().getName());
		request.setMethodName(method.getName());
		request.setParameterTypes(method.getParameterTypes());
		request.setParameters(args);
		MudfishMessage requestMessage = new MudfishMessage(MessageType.NORMAL, request);

		RpcFutureResponse response = new RpcFutureResponse(request, RpcResponseFactory.getInstance());
		clientManager.getClient().send(requestMessage);
		MudfishRpcResponse result = response.get(RpcServerConstant.CLIENT_RESPONSE_TIME_OUT);
		if (!StringUtils.isEmpty(result.getErrorMsg())) {
			throw new MudfishRpcException(result.getErrorMsg());
		}
		return result.getResult();
	}
}
