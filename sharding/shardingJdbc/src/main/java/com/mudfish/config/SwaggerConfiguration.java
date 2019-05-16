package com.mudfish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@ComponentScan(basePackages = {"com.mudfish.controller"})
//@EnableWebMvc
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	@Bean
	public Docket customDocket() {
		//
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("MUDFISH", "https://github.com/jiangweigen", null);
		return new ApiInfo("Shardingjdbc学习API接口",//大标题 title
				"springmvc集成shardingjdbc进行分库分表学习",//小标题
				"0.0.1",//版本
				"https://github.com/jiangweigen",//termsOfServiceUrl
				contact,//作者
				"我的Github",//链接显示文字
				"https://github.com/jiangweigen"//网站链接
		);
	}
}
