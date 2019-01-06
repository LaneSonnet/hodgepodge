package com.jiangwg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mudfish on 2019/1/5 0005.
 */
@EnableAutoConfiguration
@RestController
public class SpringBoot2Application {

	@RequestMapping("/")
	public void test() {
		System.out.println("hello spring boot");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2Application.class, args);
	}
}
