package com.jiangwg.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jiangwg.vo.FundVo;

/**
 * Created by Mudfish on 2019/1/10 0010.
 */
@Configuration
@EnableConfigurationProperties(AutoInject.class)
public class OtherConfig {

	@Bean
	public FundVo getFundVo() {
		return new FundVo("jiang","wei");
	}
}
