package com.jiangwg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import com.jiangwg.annotation.ExcludeFromComponentScan;
import com.jiangwg.config.OtherConfiguration;

/**
 * Created by Mudfish on 2019/1/5 0005.
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "ms-user",configuration = OtherConfiguration.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan
		.class)})
@EnableCircuitBreaker
public class MsMovieRibbonHystrixApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MsMovieRibbonHystrixApplication.class, args);
	}
}
