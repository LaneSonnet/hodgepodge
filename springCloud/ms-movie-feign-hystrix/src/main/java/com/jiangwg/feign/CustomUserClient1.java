package com.jiangwg.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.config.Configuration1;
import com.jiangwg.entity.User;

import feign.Param;
import feign.RequestLine;

/**
 * Created by Mudfish on 2019/1/13 0013.
 */
@FeignClient(value = "ms-user", configuration = Configuration1.class)
public interface CustomUserClient1 {

	@RequestLine("GET /simple/{id}")
	public User findById(@Param("id") Long id);

}
