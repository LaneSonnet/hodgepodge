package com.jiangwg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jiangwg.configuration.AutoInject;
import com.jiangwg.configuration.ConfigTest;
import com.jiangwg.vo.FundVo;

/**
 * Created by Mudfish on 2019/1/7 0007.
 */
@Component
public class MyPostRunner  implements ApplicationRunner{

	@Autowired
	private ConfigTest configTest;

	@Autowired
	private FundVo fundVo;

	@Autowired
	private AutoInject autoInject;

	@Value("${myvar.message}")
	private String message;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("注入list："  + configTest.getErrorCodes());
		System.out.println("注入map："  + configTest.getMap());
		System.out.println("注入属性："  +  message);
		System.out.println("配置生成fundvo：" + fundVo);
		System.out.println("注入配置：" + autoInject.getSecurity().toString());
		System.out.println("ApplicationRunner----------开始运行：" + applicationArguments.getSourceArgs());
	}
}
