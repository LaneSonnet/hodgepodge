package com.jiangwg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiangwg.annotation.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * Created by Mudfish on 2019/1/13 0013.
 */
@Configuration
@ExcludeFromComponentScan
public class OtherConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}
}
