package com.mudfish;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import com.mudfish.interfaces.RpcTest;

/**
 * Created by Mudfish on 2019/2/16 0016.
 */
@Component
public class RpcBeanFactory implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(RpcBeanFactory.class);

	@Autowired
	DefaultListableBeanFactory beanFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		RpcTest rpcTest = (RpcTest) Proxy
				.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{RpcTest.class}, new
						InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								logger.info("方法名【{}】，参数【{}】", method.getName(), args[0].toString());
								return "hello";
							}
						});
		beanFactory.registerSingleton("rpcTest", rpcTest);
	}
}
