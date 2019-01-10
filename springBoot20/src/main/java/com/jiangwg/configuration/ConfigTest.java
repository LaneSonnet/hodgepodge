package com.jiangwg.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Mudfish on 2019/1/10 0010.
 */
@Component
@ConfigurationProperties(prefix = "myvar")
public class ConfigTest {

	private List<String> errorCodes = new ArrayList<String>();

	private Map<String, String> map = new HashMap<>();

	public List<String> getErrorCodes() {
		return errorCodes;
	}

	public Map<String, String> getMap() {
		return map;
	}
}
