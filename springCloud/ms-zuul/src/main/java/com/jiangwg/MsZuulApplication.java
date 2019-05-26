package com.jiangwg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Mudfish on 2019/1/5 0005.
 */
@SpringBootApplication
@EnableZuulProxy
public class MsZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsZuulApplication.class, args);
	}
}
