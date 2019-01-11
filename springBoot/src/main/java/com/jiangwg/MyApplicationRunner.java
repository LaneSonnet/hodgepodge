package com.jiangwg;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jiangwg.configuration.SecurityConfig;

@Component
public class MyApplicationRunner implements ApplicationRunner{

	private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

	@Autowired
	private SecurityConfig securityConfig;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		logger.info("MyApplicationRunner运行【{}】", Arrays.toString(applicationArguments.getSourceArgs()));
		logger.info("自动注入属性【{}】", securityConfig);
	}
}
