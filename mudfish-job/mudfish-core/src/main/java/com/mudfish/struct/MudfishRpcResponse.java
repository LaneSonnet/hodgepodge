package com.mudfish.struct;

import java.io.Serializable;

public class MudfishRpcResponse implements Serializable {

	private static final long serialVersionUID = 429L;

	private String requestId;
	private String errorMsg;
	private Object result;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "MudfishRpcResponse{" +
				"requestId='" + requestId + '\'' +
				", errorMsg='" + errorMsg + '\'' +
				", result=" + result +
				'}';
	}
}
