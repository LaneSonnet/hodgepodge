package com.mudfish.factory;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.mudfish.constants.MessageType;
import com.mudfish.exception.MudfishRpcException;
import com.mudfish.struct.MudfishMessage;
import com.mudfish.struct.MudfishRpcRequest;
import com.mudfish.struct.MudfishRpcResponse;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class RpcInvokeFactory {

	private static final Logger logger = LoggerFactory.getLogger(RpcInvokeFactory.class);

	private HashMap<String, Object> serviceBeans = new HashMap<String, Object>();
	private ApplicationContext applicationContext;
	private static final long BLOCK_TIME_OUT = 30 * 1000;

	public RpcInvokeFactory(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public MudfishMessage invokeService(MudfishMessage message) {
		MudfishRpcResponse response = new MudfishRpcResponse();
		try {
			MudfishRpcRequest request = (MudfishRpcRequest) message.getBody();
			response.setRequestId(request.getRequestId());
			if (System.currentTimeMillis() - request.getCreateMillisTime() > BLOCK_TIME_OUT) {
				throw new MudfishRpcException("服务器忙，请稍后重试！");
			}
			Object obj = reflectInvoke(request);
			response.setResult(obj);
			return new MudfishMessage(MessageType.NORMAL, response);
		} catch (Exception e) {
			response.setErrorMsg(e.getMessage());
			return new MudfishMessage(MessageType.NORMAL, response);
		}
	}

	private Object reflectInvoke(MudfishRpcRequest request) throws Exception {
		Object serviceBean = serviceBeans.get(request.getClassName());
		if (serviceBean == null) {
			serviceBean = applicationContext.getBean(Class.forName(request.getClassName()));
			if (serviceBean == null) {
				throw new MudfishRpcException("未找到对应的实现类：" + request.getClassName());
			}
			serviceBeans.put(request.getClassName(), serviceBean);
		}
		Class<?> serviceClass = serviceBean.getClass();
		String methodName = request.getMethodName();
		Class<?>[] parameterTypes = request.getParameterTypes();
		Object[] parameters = request.getParameters();
		Method method = serviceClass.getMethod(methodName, parameterTypes);
		method.setAccessible(true);
		Object result = method.invoke(serviceBean, parameters);
		return result;
	}
}