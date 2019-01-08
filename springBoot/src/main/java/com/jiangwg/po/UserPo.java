package com.jiangwg.po;

public class UserPo {

	private String name;
	private String certificateType;
	private String certificateNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Override
	public String toString() {
		return "UserPo{" +
				"name='" + name + '\'' +
				", certificateType='" + certificateType + '\'' +
				", certificateNo='" + certificateNo + '\'' +
				'}';
	}
}
