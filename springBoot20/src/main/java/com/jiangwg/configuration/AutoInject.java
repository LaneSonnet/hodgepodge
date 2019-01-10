package com.jiangwg.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Mudfish on 2019/1/10 0010.
 */
@ConfigurationProperties(prefix = "myvar")
public class AutoInject {

	private Security security;

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
}
