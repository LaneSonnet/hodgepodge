package com.jiangwg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jiangwg.entity.User;
import com.jiangwg.feign.CustomUserClient1;
import com.jiangwg.feign.CustomUserClient2;

@RestController
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;
//	@Autowired
//	private UserClient userClient;
	@Autowired
	private CustomUserClient1 customUserClient1;
	@Autowired
	private CustomUserClient2 customUserClient2;

	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("ms-user");
		logger.info("请求实例id【{}】，ip【{}】，端口【{}】", serviceInstance.getServiceId(), serviceInstance.getHost(),
				serviceInstance.getPort());
		return this.restTemplate.getForObject("http://ms-user/simple/" + id, User.class);
	}

	@GetMapping("/movie/feign/custom/{id}")
	public User customerFindById(@PathVariable Long id) {
		return customUserClient1.findById(id);
	}

	@GetMapping("/movie/feign/{serviceName}")
	public String findServiceInfo(@PathVariable String serviceName) {
		return customUserClient2.findEurekaServiceInfo(serviceName);
	}

/*	@GetMapping("/movie/feign/{id}")
	public User feginFindById(@PathVariable Long id) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("ms-user");
		logger.info("请求实例id【{}】，ip【{}】，端口【{}】", serviceInstance.getServiceId(), serviceInstance.getHost(),
				serviceInstance.getPort());
		return userClient.findById(id);
	}

	@PostMapping("/movie/feign/user")
	public User feginFindUser(@RequestBody User user) {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("ms-user");
		logger.info("请求实例id【{}】，ip【{}】，端口【{}】", serviceInstance.getServiceId(), serviceInstance.getHost(),
				serviceInstance.getPort());
		logger.info("请求入参【{}】", user);
		return userClient.postUser(user);
	}*/
}
