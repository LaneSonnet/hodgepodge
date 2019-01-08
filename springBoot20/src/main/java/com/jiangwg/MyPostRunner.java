package com.jiangwg;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Mudfish on 2019/1/7 0007.
 */
@Component
public class MyPostRunner  implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("ApplicationRunner----------开始运行：" + applicationArguments.getSourceArgs());
	}
}
