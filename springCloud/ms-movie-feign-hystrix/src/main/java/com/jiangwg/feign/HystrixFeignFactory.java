package com.jiangwg.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jiangwg.entity.User;

import feign.hystrix.FallbackFactory;

/**
 * Created by Mudfish on 2019/5/23 0023.
 */
@Component
public class HystrixFeignFactory implements FallbackFactory<CustomUserClient1>{

	private static final Logger logger = LoggerFactory.getLogger(HystrixFeignFactory.class);

	@Override
	public CustomUserClient1 create(Throwable throwable) {
		logger.error("调用客户端报错，原因：" + throwable);
		return new CustomUserClient1() {
			@Override
			public User findById(Long id) {
				User user = new User();
				user.setId(-2l);
				return user;
			}
		};
	}
}
