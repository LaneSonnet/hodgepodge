package com.jiangwg.configuration;

/**
 * Created by Mudfish on 2019/1/10 0010.
 */
public class Security {

	private String username;

	private String password;
	@Override
	public String toString() {
		return "Security{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
