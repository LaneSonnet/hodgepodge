package com.mudfish.struct;

import java.io.Serializable;

/**
 * Created by Mudfish on 2019/2/17 0017.
 */
public final class Header implements Serializable {

	private static final long serialVersionUID = 42L;
	private int length;     //消息长度
	private int type;   //消息类型：MessageType

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Header{" +
				"length=" + length +
				", type=" + type +
				'}';
	}
}
