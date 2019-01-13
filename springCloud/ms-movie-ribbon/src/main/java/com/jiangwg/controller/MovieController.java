package com.jiangwg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jiangwg.entity.User;

@RestController
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("ms-user");
		logger.info("请求实例id【{}】，ip【{}】，端口【{}】", serviceInstance.getServiceId(), serviceInstance.getHost(),
				serviceInstance.getPort());
		return this.restTemplate.getForObject("http://ms-user/simple/" + id, User.class);
	}
}
