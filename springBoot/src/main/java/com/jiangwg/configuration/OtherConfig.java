package com.jiangwg.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityConfig.class)
//@Profile("product")
public class OtherConfig {

/*	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRgister = new FilterRegistrationBean();
		filterRgister.setFilter(new SignFilter());
		ArrayList<String> urlMappings = new ArrayList<String>();
		urlMappings.add("/sign*//*");
		filterRgister.setUrlPatterns(urlMappings);
		return filterRgister;
	}*/

	/**
	 * 默认匹配 /*
	 */
/*	@Bean
	public SignFilter signFilter() {
		return new SignFilter();
	}*/

}
