package com.jiangwg.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiangwg.entity.User;

/**
 * Created by Mudfish on 2019/1/13 0013.
 */
@FeignClient("ms-user")
public interface UserClient {

	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id); // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User postUser(@RequestBody User user);

}
