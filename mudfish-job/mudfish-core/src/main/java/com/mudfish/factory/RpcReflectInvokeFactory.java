package com.mudfish.factory;

import org.slf4j.Logger;

import com.mudfish.constants.MessageType;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class RpcReflectInvokeFactory {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RpcReflectInvokeFactory.class);

	public static MudfishMessage invokeService(MudfishMessage request) {
		//TODO 反射调用方法
		MudfishRpcResponse response = new MudfishRpcResponse();
		response.setRequestId(((MudfishRpcRequest)request.getBody()).getRequestId());
		response.setErrorMsg("哦了" + ((MudfishRpcRequest)request.getBody()).getRequestId());
		MudfishMessage mudfishMessage = new MudfishMessage(MessageType.NORMAL, response);
		return mudfishMessage;
	}
}
