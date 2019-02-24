package com.mudfish.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudfish.interfaces.rpc.RpcTest;

/**
 * Created by Mudfish on 2019/2/16 0016.
 */
@RestController
@DependsOn("rpcBeanRegister")
public class HomeController {

	@Autowired
	private RpcTest rpcTest;

	@RequestMapping("/")
	public Map test() {
		System.out.println("hello spring boot");
		rpcTest.testMethod("oh my god");
		HashMap<String, String> result = new HashMap<>();
		result.put("aaaa", "aaaaa");
		return result;
	}

}
