package com.mudfish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Mudfish on 2019/2/16 0016.
 */
@SpringBootApplication
@MapperScan("com.mudfish.dao")
public class MudfishAdminApplication {

	public static void main(String[] args) {
		//自定义SpringApplication
		SpringApplication springApplication = new SpringApplication(MudfishAdminApplication.class);
		springApplication.run(args);
//		SpringApplication.run(SpringBoot2Application.class, args);
	}
}
