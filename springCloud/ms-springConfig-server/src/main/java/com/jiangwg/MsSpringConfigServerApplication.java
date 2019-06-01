package com.jiangwg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by Mudfish on 2019/1/5 0005.
 */
@SpringBootApplication
@EnableConfigServer
public class MsSpringConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSpringConfigServerApplication.class, args);
	}
}
