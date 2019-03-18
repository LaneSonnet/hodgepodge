package com.mudfish.component;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import com.mudfish.factory.RpcBeanFactory;
import com.mudfish.netty.client.RpcClientManager;

/**
 * Created by Mudfish on 2019/2/24 0024.
 */
@Component
public class RpcBeanRegister implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(RpcBeanRegister.class);

	@Autowired
	private DefaultListableBeanFactory beanFactory;
	@Autowired
	private RpcClientManager clientManager;

	@Override
	public void afterPropertiesSet() throws Exception {
		RpcBeanFactory rpcBeanFactory = new RpcBeanFactory(clientManager);
		Map<String, Object> rpcProxyBeans = rpcBeanFactory.getRpcProxyBeans();
		for (Entry<String, Object> beans : rpcProxyBeans.entrySet()) {
			beanFactory.registerSingleton(beans.getKey(), beans.getValue());
		}
	}
}
