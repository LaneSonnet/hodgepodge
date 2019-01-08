package com.jiangwg.vo;

/**
 * Created by Mudfish on 2019/1/1 0001.
 */
public class FundVo {
	private String fundName;
	private String fundCode;

	@Override
	public String toString() {
		return "FundVo{" +
				"fundName='" + fundName + '\'' +
				", fundCode='" + fundCode + '\'' +
				'}';
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
}
