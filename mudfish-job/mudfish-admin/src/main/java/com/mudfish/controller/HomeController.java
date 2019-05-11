package com.mudfish.controller;

import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mudfish.component.RpcBeanRegister;
import com.mudfish.interfaces.rpc.RpcTest;
import com.mudfish.vo.TestVo;

import io.protostuff.Request;

/**
 * Created by Mudfish on 2019/2/16 0016.
 */
@RestController
@DependsOn("rpcBeanRegister")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private RpcTest rpcTest;

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Map test(@RequestBody TestVo testVo) {
		logger.info("接受参数【{}】", testVo);
		String resultStr = rpcTest.testMethod(testVo.toString());
		HashMap<String, String> result = new HashMap<>();
		result.put("result", resultStr);
		return result;
	}

}
