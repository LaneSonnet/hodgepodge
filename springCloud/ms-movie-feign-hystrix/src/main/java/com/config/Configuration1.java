package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;
import feign.Logger.Level;

/**
 * Created by Mudfish on 2019/5/21 0021.
 */
@Configuration
public class Configuration1 {

	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Level.FULL;
	}

}
