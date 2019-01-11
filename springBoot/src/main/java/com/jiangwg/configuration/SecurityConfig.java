package com.jiangwg.configuration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "myvar")
@Validated
public class SecurityConfig {

	@NotNull
	private String privateKey;
	@NotNull
	private String publicKey;

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public String toString() {
		return "SecurityConfig{" +
				"privateKey='" + privateKey + '\'' +
				", publicKey='" + publicKey + '\'' +
				'}';
	}
}
