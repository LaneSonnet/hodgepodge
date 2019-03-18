package com.mudfish.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Mudfish on 2019/2/24 0024.
 */
@Component
@ConfigurationProperties(prefix = "mudfish-admin")
public class ServerConfig {

	private List<String> serverParams = new ArrayList<>();

	public List<String> getServerParams() {
		return serverParams;
	}
}
