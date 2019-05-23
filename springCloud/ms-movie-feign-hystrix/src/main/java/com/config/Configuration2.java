package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger.Level;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * Created by Mudfish on 2019/5/21 0021.
 */
@Configuration
public class Configuration2 {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "12345678");
	}

}
