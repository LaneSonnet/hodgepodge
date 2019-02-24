package com.mudfish.service;

import org.springframework.stereotype.Service;

import com.mudfish.interfaces.rpc.RpcTest;

/**
 * Created by Mudfish on 2019/2/24 0024.
 */
@Service
public class RpcTestImpl implements RpcTest {

	@Override
	public String testMethod(String str) {
		return "rpcTestImpl 收到收到";
	}
}
