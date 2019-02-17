package com.mudfish.struct;

import java.io.Serializable;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public class MudfishMessage implements Serializable{

	private static final long serialVersionUID = 22L;
	private Header header;
	private Object body;

	public MudfishMessage() {
	}

	public MudfishMessage(int type, Object obj) {
		Header header = new Header();
		header.setType(type);
		this.header = header;
		this.body = obj;
	}


	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "MudfishMessage{" +
				"header=" + header +
				", body=" + body +
				'}';
	}
}
