package com.jiangwg.po;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "myvar.random")
public class RandomPro {

	private String secret;
	private int number;
	private long bignumber;
	private String uuid;
	private int lessThanNum;
	private int rangeNum;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getBignumber() {
		return bignumber;
	}

	public void setBignumber(long bignumber) {
		this.bignumber = bignumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getLessThanNum() {
		return lessThanNum;
	}

	public void setLessThanNum(int lessThanNum) {
		this.lessThanNum = lessThanNum;
	}

	public int getRangeNum() {
		return rangeNum;
	}

	public void setRangeNum(int rangeNum) {
		this.rangeNum = rangeNum;
	}

	@Override
	public String toString() {
		return "RandomPro{" +
				"secret='" + secret + '\'' +
				", number=" + number +
				", bignumber=" + bignumber +
				", uuid='" + uuid + '\'' +
				", lessThanNum=" + lessThanNum +
				", rangeNum=" + rangeNum +
				'}';
	}
}
