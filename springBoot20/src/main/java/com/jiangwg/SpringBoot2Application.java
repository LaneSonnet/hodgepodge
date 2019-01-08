package com.jiangwg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangwg.listener.MyApplicationListener;

/**
 * Created by Mudfish on 2019/1/5 0005.
 */
@SpringBootApplication
@RestController
@MapperScan("com.jiangwg.dao")
public class SpringBoot2Application {

	@RequestMapping("/")
	public void test() {
		System.out.println("hello spring boot");
	}

	public static void main(String[] args) {
		//自定义SpringApplication
		SpringApplication springApplication = new SpringApplication(SpringBoot2Application.class);
		springApplication.addListeners(new MyApplicationListener());
		springApplication.run(args);

//		SpringApplication.run(SpringBoot2Application.class, args);
	}
}
