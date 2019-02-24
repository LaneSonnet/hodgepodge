package com.mudfish.factory;

import java.io.File;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mudfish.netty.client.RpcClientManager;

/**
 * Created by Mudfish on 2019/2/16 0016.
 */
public class RpcBeanFactory {

	private static final Logger logger = LoggerFactory.getLogger(RpcBeanFactory.class);

	private static final String INTERFACE_PATH = "com/mudfish/interfaces/rpc";
	private static final String INTERFACE_PACKAGE_PATH = "com.mudfish.interfaces.rpc";
	private static final String CLASS_SUFIX = ".class";

	private RpcClientManager clientManager;

	public RpcBeanFactory(RpcClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public Map<String, Object> getRpcProxyBeans() throws Exception {
		String realPath = Thread.currentThread().getContextClassLoader().getResource(INTERFACE_PATH).getPath();
		File file = new File(realPath);
		HashMap<String, Object> result = new HashMap<String, Object>();
		instanceClass(file, result);
		return result;
	}

	private void instanceClass(File file, HashMap<String, Object> result) throws ClassNotFoundException {
		if (file.isFile()) {
			String className = file.getName().replace(CLASS_SUFIX, "");
			Object obj = this.getInstance(getCompleteClassName(file));
			result.put(lowerFirst(className), obj);
			return;
		}
		File[] files = file.listFiles();
		if (files.length == 0) {
			return;
		}
		for (File subFile : files) {
			instanceClass(subFile, result);
		}
	}

	private String getCompleteClassName(File file) {
		String path = file.getAbsolutePath().replace(CLASS_SUFIX, "").replace(File.separator, ".");
		return path.substring(path.indexOf(INTERFACE_PACKAGE_PATH));
	}

	private Object getInstance(String className) throws ClassNotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class[] classes = {Class.forName(className)};
		RpcInvokeHandler invokeHandler = new RpcInvokeHandler(clientManager);
		return Proxy.newProxyInstance(classLoader, classes,invokeHandler);
	}

	public static String lowerFirst(String oldStr) {
		char[] chars = oldStr.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

}
