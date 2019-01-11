package com.jiangwg.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityConfig.class)
//@Profile("product")
public class OtherConfig {

}
