package com.mudfish.exception;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class MudfishRpcException extends RuntimeException {

	private static final long serialVersionUID = 42L;

	public MudfishRpcException(String msg) {
		super(msg);
	}

	public MudfishRpcException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MudfishRpcException(Throwable cause) {
		super(cause);
	}
}
