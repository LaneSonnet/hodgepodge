package com.jiangwg.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.config.Configuration1;
import com.config.Configuration2;
import com.jiangwg.entity.User;

import feign.RequestLine;

/**
 * Created by Mudfish on 2019/1/13 0013.
 */
@FeignClient(name = "userFeignClient", url = "http://localhost:8761/", configuration = Configuration2.class)
public interface CustomUserClient2 {

	@RequestMapping(value = "/eureka/apps/{serviceName}")
	public String findEurekaServiceInfo(@PathVariable("serviceName") String serviceName);

}
