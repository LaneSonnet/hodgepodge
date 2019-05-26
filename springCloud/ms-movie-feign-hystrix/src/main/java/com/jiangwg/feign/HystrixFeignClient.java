package com.jiangwg.feign;

import org.springframework.stereotype.Component;

import com.jiangwg.entity.User;

/**
 * Created by Mudfish on 2019/5/23 0023.
 */
@Component
public class HystrixFeignClient implements CustomUserClient1 {

	@Override
	public User findById(Long id) {
		User user = new User();
		user.setId(-1l);
		return user;
	}
}
